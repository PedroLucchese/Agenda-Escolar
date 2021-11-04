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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CoordenadorDAO;
import dao.DisciplinaDAO;
import dao.TurmaDAO;
import dao.UsuarioDAO;
import dao.ProfessorDAO;

import entity.Coordenador;
import entity.Usuario;
import entity.Professor;

public class CoordenadorView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;
	private JLabel lblDisciplina, lblTurma, lblProfessor;
	
	private JTextField tDisciplina, tTurma, tAluno;

	private JScrollPane rolagem;
	private JTable tableProfessores;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	private Coordenador coordenador;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(900, 500);

		criaJTable();
		
		tDisciplina = new JTextField("");
		tTurma = new JTextField("");
		tAluno = new JTextField("");

		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);

		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.setLayout(null);
		
		rolagem = new JScrollPane(tableProfessores);
		
		lblDisciplina = new JLabel("Nome da disciplina:");
		lblTurma = new JLabel("Nome da turma:");
		lblProfessor = new JLabel("Professor:");
		
		setComponentsBounds();
		
		campos.add(lblDisciplina);
		campos.add(tDisciplina);
		campos.add(lblTurma);
		campos.add(tTurma);
		campos.add(lblProfessor);
		campos.add(rolagem);
		
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

	public CoordenadorView(Coordenador coordenador, Connection connection) {
		this.connection = connection;
		this.coordenador = coordenador;

		this.init();
	}
	
	private void setComponentsBounds() {
		lblDisciplina.setBounds(100, 50, 200, 100);
		lblTurma.setBounds(100, 100, 200, 100);
		lblProfessor.setBounds(100, 200, 200, 100);
		tDisciplina.setBounds(230, 85, 300, 30);
		tTurma.setBounds(230, 135, 300, 30);
		rolagem.setBounds(230, 200, 500, 200);
	}

	private void acaoLimpar() {
		tDisciplina.setText("");
		tTurma.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {
		
		
		final DisciplinaDAO disciplinaDao = new DisciplinaDAO(connection);

		

		JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");

		//new NavegaView(usuario, connection).setVisible(true);
		//this.dispose();
		
	}
	
	private void criaJTable() {
		tableProfessores = new JTable(modelo);
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		tableProfessores.getColumnModel().getColumn(0)
		.setPreferredWidth(10);
		tableProfessores.getColumnModel().getColumn(1)
		.setPreferredWidth(120);
		pesquisar(modelo);
	}

	private void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		
		ProfessorDAO professorDAO = new ProfessorDAO(connection);
		
		for (Professor professor : professorDAO.findAll()) {
			System.out.print(professor.getId());
			modelo.addRow(new Object[] {professor.getId(), professor.getNome()});
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

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

}

