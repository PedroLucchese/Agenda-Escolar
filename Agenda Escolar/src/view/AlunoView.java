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

import dao.UsuarioDAO;
import entity.Aluno;
import entity.Usuario;

public class AlunoView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bVolta;
	private JPanel fundo, botoes, campos;

	private JTextField tNota1, tNota2, tNota3, tMedia;

	private Aluno aluno;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tNota1 = new JTextField("");
		tNota2 = new JTextField("");
		tNota3 = new JTextField("");
		tMedia = new JTextField("");

		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Nota 1:"));
		campos.add(tNota1);
		campos.add(new JLabel("Nota 2:"));
		campos.add(tNota2);
		campos.add(new JLabel("Nota 3:"));
		campos.add(tNota3);
		campos.add(new JLabel("M�dia:"));
		campos.add(tMedia);

		botoes.add(bVolta);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public AlunoView(Aluno find, Connection connection) {
		this.connection = connection;
		this.aluno = find;

		this.init();
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} 

	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}

