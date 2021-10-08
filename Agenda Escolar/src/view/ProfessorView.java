package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Professor;
import entity.Usuario;

public class ProfessorView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tMateria, tAluno, tNota1, tNota2, tNota3;

	private Professor professor;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tMateria = new JTextField("");
		tAluno = new JTextField("");
		tNota1 = new JTextField("");
		tNota2 = new JTextField("");
		tNota3 = new JTextField("");

		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Mat�ria:"));
		campos.add(tMateria);
		campos.add(new JLabel("Aluno:"));
		campos.add(tAluno);
		campos.add(new JLabel("Nota 1:"));
		campos.add(tNota1);
		campos.add(new JLabel("Nota 2:"));
		campos.add(tNota2);
		campos.add(new JLabel("Nota 3:"));
		campos.add(tNota3);

		botoes.add(bCadastra);
		botoes.add(bVolta);
		botoes.add(bLimpa);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public ProfessorView(Professor professor, Connection connection) {
		this.professor = professor;
		this.connection = connection;

		this.init();
	}

	private void acaoLimpar() {
		tMateria.setText("");
		tAluno.setText("");
		tNota1.setText("");
		tNota2.setText("");
		tNota3.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {

		this.professor.setNome(tMateria.getText());
		this.professor.setNome(tAluno.getText());
		this.professor.setSenha(tNota1.getText());
		this.professor.setSenha(tNota2.getText());
		this.professor.setSenha(tNota3.getText());

		final ProfessorDAO dao = new ProfessorDAO(connection);

		dao.saveOrUpdate(professor);

		professor = dao.find(professor);

		JOptionPane.showMessageDialog(this, "Notas cadastrada com sucesso!");

		//new NavegaView(professor, connection).setVisible(true);
		//this.dispose();

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bCadastra)) {
			this.acaoCadastra();
		} else if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} else if (e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}

