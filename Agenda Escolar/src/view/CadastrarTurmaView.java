package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dao.AlunoDAO;
import dao.CoordenadorDAO;
import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import dao.TurmaDAO;
import entity.Aluno;
import entity.Coordenador;
import entity.Disciplina;
import entity.Professor;
import entity.Turma;

public class CadastrarTurmaView extends JFrame implements ActionListener {
	Connection connection;
	
	private Turma turma;
	private Coordenador coordenador;
	
	private JPanel fundo, botoes, campos;
	private JButton bCadastrarTurma, bVoltar, bLimpar;
	private JLabel lblNome, lblDescricao, lblHorario, lblProfessor, lblAlunos, lblDisciplina;
	private JTextField tNome, tDescricao, tHorario;
	
	private JList listProfessores, listAlunos, listDisciplina;
	private DefaultListModel modeloProfessor, modeloAluno, modeloDisciplina;
	private JScrollPane rolagemProfessores, rolagemAlunos, rolagemDisciplina;
	
	public CadastrarTurmaView(Connection connection, Coordenador coordenador) {
		this.connection = connection;
		this.coordenador = coordenador;
		
		this.init();
	}
	
	private void init() {
		
		this.setTitle("Cadastro de turma");
		this.setSize(900, 900);
		
		modeloProfessor = new DefaultListModel();
		modeloAluno = new DefaultListModel();
		modeloDisciplina = new DefaultListModel();
		CriaJLists();
		
		lblNome = new JLabel("Nome da turma:");
		lblDescricao = new JLabel("Descrição da turma:");
		lblProfessor = new JLabel("Professor:");
		lblHorario = new JLabel("Horário");
		lblAlunos = new JLabel("Alunos:");
		lblDisciplina = new JLabel("Disciplina:");
		
		tNome = new JTextField("");
		tDescricao = new JTextField("");
		tHorario = new JTextField("");
		
		bCadastrarTurma = new JButton("Cadastrar turma");
		bCadastrarTurma.addActionListener(this);
		bVoltar = new JButton("Voltar");
		bVoltar.addActionListener(this);
		
		bLimpar = new JButton("Limpar");
		bLimpar.addActionListener(this);
		
		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		rolagemProfessores = new JScrollPane(listProfessores);
		rolagemAlunos = new JScrollPane(listAlunos);
		rolagemDisciplina = new JScrollPane(listDisciplina);
		
		campos.setLayout(null);
		
		setComponentsBounds();
		
		campos.add(lblNome);
		campos.add(lblDescricao);
		campos.add(lblProfessor);
		campos.add(lblHorario);
		campos.add(lblAlunos);
		campos.add(lblDisciplina);
		
		campos.add(tNome);
		campos.add(tDescricao);
		campos.add(tHorario);
		
		campos.add(rolagemProfessores);
		campos.add(rolagemAlunos);
		campos.add(rolagemDisciplina);
		
		botoes.add(bCadastrarTurma);
		botoes.add(bVoltar);
		botoes.add(bLimpar);
		
		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);
		
		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	private void CriaJLists() {
		listProfessores = new JList(modeloProfessor);
		listAlunos = new JList(modeloAluno);
		listDisciplina = new JList(modeloDisciplina);
		
		pesquisarProfessores(modeloProfessor);
		pesquisarAlunos(modeloAluno);
		pesquisarDisciplinas(modeloDisciplina);
	}
	
	private void pesquisarDisciplinas(DefaultListModel modelo) {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO(connection);
		
		for (Disciplina disciplina : disciplinaDAO.findAll()) {
			modelo.addElement(new Disciplina(disciplina.getId(), disciplina.getNome(), disciplina.getCodigo()));
		}
	}
	
	private void pesquisarProfessores(DefaultListModel modelo) {
		ProfessorDAO professorDAO = new ProfessorDAO(connection);
		
		for (Professor professor : professorDAO.findAll()) {
			modelo.addElement(new Professor(professor.getId(), professor.getNome()));
		}
	}
	
	private void pesquisarAlunos(DefaultListModel modelo) {
		AlunoDAO alunoDAO = new AlunoDAO(connection);
		
		for (Aluno aluno : alunoDAO.findAll()) {
			modelo.addElement(new Aluno(aluno.getId(), aluno.getNome()));
		}
	}
	
	private void setComponentsBounds() {
		lblNome		.setBounds(100, 10, 200, 20);
		lblDescricao.setBounds(100, 50, 200, 20);
		lblHorario	.setBounds(100, 90, 200, 20);
		lblAlunos	.setBounds(100, 250, 200, 20);
		lblProfessor.setBounds(100, 450, 200, 20);
		lblDisciplina.setBounds(100, 620, 200, 20);
		
		tNome		.setBounds(230, 5, 300, 30);
		tDescricao	.setBounds(230, 45, 300, 30);
		tHorario	.setBounds(230, 85, 300, 30);
		
		rolagemAlunos.setBounds(230, 175, 500, 150);
		rolagemProfessores.setBounds(230, 375, 500, 150);
		rolagemDisciplina.setBounds(230, 575, 500, 150);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bVoltar)) {
			this.acaoVoltar();
		} else if (e.getSource().equals(bLimpar)) {
			this.acaoLimpar();
		} else if (e.getSource().equals(bCadastrarTurma)) {
			this.acaoCadastrar();
		}
	}
	
	private int getIdProfessor() {
		Matcher matcher = Pattern.compile("\\d+").matcher(listProfessores.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private int getIdDisciplina() {
		Matcher matcher = Pattern.compile("\\d+").matcher(listDisciplina.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private ArrayList<Integer> getIdsAlunos() {
		ArrayList<Integer> idsAlunos = new ArrayList<Integer>();
		
		for (Object linha : listAlunos.getSelectedValues()) {
			Matcher matcher = Pattern.compile("\\d+").matcher(linha.toString());
			matcher.find();
			idsAlunos.add(Integer.valueOf(matcher.group()));
		}
		
		return idsAlunos;
	}
	
	private void acaoCadastrar() {
		final ProfessorDAO professorDao = new ProfessorDAO(connection);
		final TurmaDAO turmaDAO = new TurmaDAO(connection);
		final AlunoDAO alunoDAO = new AlunoDAO(connection);
		final DisciplinaDAO disciplinaDAO = new DisciplinaDAO(connection);
		
		Professor professor;
		Disciplina disciplina;
		Turma turma = new Turma();
		
		turma.setNome(tNome.getText());
		turma.setDescricao(tDescricao.getText());
		turma.setHorario(tHorario.getText());
		
		professor = professorDao.findById(getIdProfessor());
		disciplina = disciplinaDAO.findById(getIdDisciplina());
		
		turma.setProfessor(professor);
		turma.setDisciplina(disciplina);
		turma.setAlunos(getIdsAlunos());
		
		turmaDAO.saveOrUpdate(turma);
		turma.setId(turmaDAO.find(turma).getId());
		
		 for (int idAluno : getIdsAlunos()) {
			Aluno aluno = alunoDAO.findById(idAluno);
			
			aluno.setTurma(turma);
			alunoDAO.updateTurma(aluno);
		}
		
		JOptionPane.showMessageDialog(this, "Turma cadastrada com sucesso!");
	}
	
	private void acaoLimpar() {
		tNome.setText("");
		tDescricao.setText("");
		tHorario.setText("");
	}
	
	private void acaoVoltar() {
		new CoordenadorView(coordenador, connection).setVisible(true);
		this.dispose();
	}
}