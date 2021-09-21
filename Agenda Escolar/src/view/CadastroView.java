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
import entity.Restricao;
import entity.Usuario;

public class CadastroView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tUsuario, tSenha, tConfirma, tTipo;

	private Usuario usuario;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tUsuario = new JTextField("");
		tTipo = new JTextField("");
		tSenha = new JTextField("");
		tConfirma = new JTextField("");

		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Usu�rio:"));
		campos.add(tUsuario);
		campos.add(new JLabel("Tipo do Usu�rio:"));
		campos.add(tTipo);
		campos.add(new JLabel("Senha:"));
		campos.add(tSenha);
		campos.add(new JLabel("Confirmar senha:"));
		campos.add(tConfirma);

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

	public CadastroView(Connection connection) {
		this.connection = connection;
		this.usuario = new Usuario();

		this.init();
	}

	private void acaoLimpar() {
		tUsuario.setText("");
		tTipo.setText("");
		tSenha.setText("");
		tConfirma.setText("");

	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {

		this.usuario.setNome(tUsuario.getText());
		this.usuario.setNome(tTipo.getText());
		this.usuario.setSenha(tSenha.getText());

		if (validaSenha(usuario.getSenha().trim(), tConfirma.getText().trim())) {
			final UsuarioDAO dao = new UsuarioDAO(connection);

			dao.saveOrUpdate(usuario);

			usuario = dao.find(usuario);

			JOptionPane.showMessageDialog(this, "usu�rio cadastrado com sucesso!");

			new NavegaView(usuario, connection).setVisible(true);
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(this, "a senha deve ser confirmada!");
		}
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

	public boolean validaSenha(final String senha, final String confirmaSenha) {
		return senha.equals(confirmaSenha);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

