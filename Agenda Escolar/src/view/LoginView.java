package view;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AlunoDAO;
import dao.UsuarioDAO;
import dao.CoordenadorDAO;
import dao.ProfessorDAO;
import entity.Aluno;
import entity.Usuario;
import entity.Coordenador;
import entity.Professor;

public class LoginView extends JFrame implements ActionListener {
	private Connection connection = null;
	private UsuarioDAO usuarioDAO;
	private AlunoDAO alunoDAO;
	private CoordenadorDAO coordenadorDAO;
	private ProfessorDAO professorDAO;

	private JButton bEntra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tUsuario, tSenha, tTipo;

	private void init() {
		this.usuarioDAO = new UsuarioDAO(connection);
		this.alunoDAO = new AlunoDAO(connection);
		this.coordenadorDAO = new CoordenadorDAO(connection);
		this.professorDAO = new ProfessorDAO(connection);

		this.setTitle("Login");
		this.setSize(500, 200);

		tUsuario = new JTextField("");
		tSenha = new JTextField("");
		tTipo = new JTextField("");

		bEntra = new JButton("Entrar");
		bEntra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Usuário:"));
		campos.add(tUsuario);
		campos.add(new JLabel("Senha:"));
		campos.add(tSenha);
		campos.add(new JLabel("Tipo:"));
		campos.add(tTipo);

		botoes.add(bEntra);
		botoes.add(bVolta);
		botoes.add(bLimpa);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public LoginView(Connection connection) {
		this.connection = connection;
		this.init();
	}

	private void acaoLimpar() {
		tUsuario.setText("");
		tSenha.setText("");
		tTipo.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoEntrar() {
		String tipo = tTipo.getText();
		
		switch (tipo) {
		case "1":
			final Aluno aluno = new Aluno();
			
			aluno.setNome(tUsuario.getText());
			aluno.setSenha(tSenha.getText());
			aluno.setTipo(tTipo.getText());

			final Aluno alunoFind = getAlunoDAO().find(aluno);

			if (alunoFind.getId() == null) {
				JOptionPane.showMessageDialog(this, "usuário não cadastrado!");
			} else {
				new AlunoView (alunoFind,connection).setVisible(true);
				this.dispose();
			}
			break;
		
		case "2":
			final Professor professor = new Professor();
			
			professor.setNome(tUsuario.getText());
			professor.setSenha(tSenha.getText());
			professor.setTipo(tTipo.getText());

			final Professor professorFind = getProfessorDAO().find(professor);

			if (professorFind.getId() == null) {
				JOptionPane.showMessageDialog(this, "usuário não cadastrado!");
			} else {
				new ProfessorView(professorFind,connection).setVisible(true);
				this.dispose();
			}
			break;
		case "3":
			final Coordenador coordenador = new Coordenador();
			
			coordenador.setNome(tUsuario.getText());
			coordenador.setSenha(tSenha.getText());
			coordenador.setTipo(tTipo.getText());

			final Coordenador coordenadorFind = getCoordenadorDAO().find(coordenador);

			if (coordenadorFind.getId() == null) {
				JOptionPane.showMessageDialog(this, "usuário não cadastrado!");
			} else {
				new CoordenadorView(coordenadorFind, connection).setVisible(true);
				this.dispose();
			}
			break;
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bEntra)) {
			this.acaoEntrar();
		} else if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} else if (e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}

	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}
	
	public CoordenadorDAO getCoordenadorDAO() {
		return coordenadorDAO;
	}

	public void setCoordenadorDAO(CoordenadorDAO coordenadorDAO) {
		this.coordenadorDAO = coordenadorDAO;
	}
	
	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

}

