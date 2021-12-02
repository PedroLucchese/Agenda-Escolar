package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.AlunoDAO;
import dao.ComponenteAvaliacaoDAO;
import dao.ProfessorDAO;
import dao.TurmaAlunoDAO;
import dao.TurmaDAO;
import dao.UsuarioDAO;
import entity.Aluno;
import entity.ComponenteAvaliacao;
import entity.Professor;
import entity.Turma;
import entity.TurmaAluno;
import entity.Usuario;

public class ProfessorView extends JFrame implements ActionListener, ListSelectionListener {
	Connection connection;

	private JButton bCadastra, bCadastraModoAvaliacao, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;
	private JLabel lblAluno, lblTurma;
	
	private JScrollPane rolagemTurmas, rolagemAlunos;
	private JList listaTurmas, listaAlunos;
	private DefaultListModel modeloTurmas, modeloAlunos;

	private Professor professor;

	private ArrayList<JTextField> tNotas;
	
	private void init() {

		this.setTitle("Cadastro");
		this.setSize(900, 900);

		modeloTurmas = new DefaultListModel();
		criaJListTurmas();
		criaJListAlunos();
		
		listaTurmas.addListSelectionListener(this);
		
		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bCadastraModoAvaliacao = new JButton("Cadastrar modo de avaliação");
		bCadastraModoAvaliacao.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);
		
		lblAluno = new JLabel("Aluno:");
		lblTurma = new JLabel("Turma:");

		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		rolagemTurmas = new JScrollPane(listaTurmas);
		rolagemAlunos = new JScrollPane(listaAlunos);
		
		campos.setLayout(null);
		
		setComponentsBounds();

		campos.add(lblAluno);
		campos.add(lblTurma);

		campos.add(rolagemTurmas);
		campos.add(rolagemAlunos);
		
		botoes.add(bCadastra);
		botoes.add(bCadastraModoAvaliacao);
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
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void setComponentsBounds() {
		lblTurma.setBounds(100, 50, 200, 100);
		lblAluno.setBounds(100, 300, 200, 100);
		rolagemTurmas.setBounds(230, 5, 500, 225);
		rolagemAlunos.setBounds(230, 250, 500, 225);
	}
	
	private void acaoCadastra() {

		final ProfessorDAO dao = new ProfessorDAO(connection);

		dao.saveOrUpdate(professor);

		professor = dao.find(professor);

		JOptionPane.showMessageDialog(this, "Notas cadastrada com sucesso!");

		//new NavegaView(professor, connection).setVisible(true);
		//this.dispose();

		
	}
	
	private void acaoAbrirCadastroModoAvaliacao() {
		
		new ModoAvaliacaoView(connection, professor).setVisible(true);
		this.dispose();
	}
	
	private void criaTextFieldNotas() {
		
		ComponenteAvaliacaoDAO componentesAvaliacaoDAO = new ComponenteAvaliacaoDAO(connection);
		int lblVar, tVar;
		
		lblVar = 500;
		tVar = 525;
		
		
		for (ComponenteAvaliacao componenteAvaliacao : componentesAvaliacaoDAO.findByTurmaId(getIdTurma())) {
			JTextField tNota = new JTextField();
			JLabel lblNota = new JLabel("Nota " + componenteAvaliacao.getComponente());
			
			lblNota.setBounds(100, lblVar, 200, 100);
			tNota.setBounds(230, tVar, 200, 40);
			
			lblVar += 50;
			tVar += 50;
			
			campos.add(tNota);
			campos.add(lblNota);
			tNotas.add(tNota);
		}
		
		campos.revalidate();
		campos.repaint();
		fundo.add(campos, BorderLayout.CENTER);

		this.getContentPane().add(fundo);
		
	}
	
	private void criaJListAlunos() {
		modeloAlunos = new DefaultListModel();
		listaAlunos = new JList(modeloAlunos);
	}
	
	private void pesquisarAlunos(DefaultListModel modelo) {
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO(connection);
		AlunoDAO alunoDAO = new AlunoDAO(connection);
		
		int idTurma = getIdTurma();
		
		for (TurmaAluno turmaAluno : turmaAlunoDAO.findByTurmaId(idTurma)) {
			Aluno aluno = alunoDAO.findById(turmaAluno.getIdAluno());
			
			modelo.addElement(new Aluno(aluno.getId(), aluno.getNome()));
		}
	}
	
	private void criaJListTurmas() {
		listaTurmas = new JList(modeloTurmas);
		pesquisarTurmas(modeloTurmas);
	}
	
	private int getIdTurma() {
		Matcher matcher = Pattern.compile("\\d+").matcher(listaTurmas.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private void pesquisarTurmas(DefaultListModel modelo) {
		TurmaDAO turmaDAO = new TurmaDAO(connection);
		
		for (Turma turma : turmaDAO.findByProfessorId(professor.getId())) {
			modelo.addElement(new Turma(turma.getId(), turma.getNome()));
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
		} else if (e.getSource().equals(bCadastraModoAvaliacao)) {
			this.acaoAbrirCadastroModoAvaliacao();
		}

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(listaTurmas)) {
			modeloAlunos.removeAllElements();
			criaTextFieldNotas();
			pesquisarAlunos(modeloAlunos);
		}
	}
}