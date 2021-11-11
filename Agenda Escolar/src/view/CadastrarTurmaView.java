package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Coordenador;
import entity.Turma;

public class CadastrarTurmaView extends JFrame implements ActionListener {
	Connection connection;
	
	private Turma turma;
	
	private JPanel fundo, botoes, campos;
	private JButton bCadastrarTurma, bVoltar, bLimpar;
	private JLabel lblNome, lblDescricao, lblHorario, lblProfessor, lblAlunos;
	
	public CadastrarTurmaView(Connection connection) {
		this.connection = connection;
		this.turma = turma;

		this.init();
	}
	
	private void init() {
		
		this.setTitle("Cadastro de turma");
		this.setSize(900, 500);
		
		bCadastrarTurma = new JButton("Cadastrar turma");
		bCadastrarTurma.addActionListener(this);
		bVoltar = new JButton("Voltar");
		bVoltar.addActionListener(this);
		bLimpar = new JButton("Limpar");
		bLimpar.addActionListener(this);
		
		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		campos.setLayout(null);
		
		
		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);
		
		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}