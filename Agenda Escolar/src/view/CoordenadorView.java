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

import dao.CoordenadorDAO;
import dao.UsuarioDAO;
import entity.Coordenador;
import entity.Usuario;

public class CoordenadorView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tMateria, tTurma, tAluno, tProfessor;

	private Coordenador coordenador;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tMateria = new JTextField("");
		tTurma = new JTextField("");
		tAluno = new JTextField("");
		tProfessor = new JTextField("");

		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Materia:"));
		campos.add(tMateria);
		campos.add(new JLabel("Turma:"));
		campos.add(tAluno);
		campos.add(new JLabel("Alunos:"));
		//campos.add(tNota1);
		campos.add(new JLabel("Professor:"));
		//campos.add(tNota2);

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

	public CoordenadorView( Coordenador coordenador, Connection connection) {
		this.connection = connection;
		this.coordenador = coordenador;

		this.init();
	}

	private void acaoLimpar() {
		tMateria.setText("");
		tTurma.setText("");
		tAluno.setText("");
		tProfessor.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {

		this.coordenador.setNome(tMateria.getText());
		this.coordenador.setNome(tTurma.getText());
		this.coordenador.setSenha(tAluno.getText());
		this.coordenador.setSenha(tProfessor.getText());

		final CoordenadorDAO dao = new CoordenadorDAO(connection);

		dao.saveOrUpdate(coordenador);

		coordenador = dao.find(coordenador);

		JOptionPane.showMessageDialog(this, "Mat�ria cadastrada com sucesso!");

		//new NavegaView(usuario, connection).setVisible(true);
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

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

}

