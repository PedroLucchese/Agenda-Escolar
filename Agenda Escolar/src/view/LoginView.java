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

import dao.UsuarioDAO;
import entity.Usuario;

public class LoginView extends JFrame implements ActionListener {
	private Connection connection = null;
	private UsuarioDAO usuarioDAO;

	private JButton bEntra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tUsuario, tSenha, tTipo;

	private void init() {
		this.usuarioDAO = new UsuarioDAO(connection);

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
		final Usuario model = new Usuario();
		model.setNome(tUsuario.getText());
		model.setSenha(tSenha.getText());
		model.setTipo(tTipo.getText());

		final Usuario find = getUsuarioDAO().find(model);

		if (find.getId() == null) {
			JOptionPane.showMessageDialog(this, "usuário não cadastrado!");
		} else {
			
			switch (find.getTipo()) {
				case "1":
						new AlunoView (find,connection).setVisible(true);
					break;
					
				case "2":
						new ProfessorView(find, connection).setVisible(true);
					break;
					
				case "3":
						new CoordenadorView(find, connection).setVisible(true);
					break;
			}
			this.dispose();
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

}

