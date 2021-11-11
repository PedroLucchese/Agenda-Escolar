package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.CoordenadorDAO;
import dao.DisciplinaDAO;
import dao.TurmaDAO;
import dao.UsuarioDAO;
import dao.ProfessorDAO;

import entity.Coordenador;
import entity.Disciplina;
import entity.Usuario;
import entity.Professor;

public class CoordenadorView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bCadastra, bVolta, bLimpa, bCadastrarTurma;
	private JPanel fundo, botoes, campos;
	private JLabel lblDisciplina, lblProfessor, lblCodigo;
	
	private JTextField tDisciplina, tAluno, tCodigo;

	private JScrollPane rolagem;
	private JList tableProfessores;
	private DefaultListModel modelo = new DefaultListModel();
	
	private Coordenador coordenador;

	private void init() {

		this.setTitle("Cadastro de disciplina");
		this.setSize(900, 500);

		criaJTable();
		
		tDisciplina = new JTextField("");
		tAluno = new JTextField("");
		tCodigo = new JTextField("");

		bCadastra = new JButton("Cadastrar disciplina");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);
		bCadastrarTurma = new JButton("Cadastrar turma");
		bCadastrarTurma.addActionListener(this);

		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.setLayout(null);
		
		rolagem = new JScrollPane(tableProfessores);
		
		lblDisciplina = new JLabel("Nome da disciplina:");
		lblProfessor = new JLabel("Professor:");
		lblCodigo = new JLabel("Código da disciplina:");
		
		setComponentsBounds();
		
		campos.add(lblDisciplina);
		campos.add(tDisciplina);
		campos.add(lblProfessor);
		campos.add(lblCodigo);
		campos.add(tCodigo);
		campos.add(rolagem);
		
		botoes.add(bCadastra);
		botoes.add(bVolta);
		botoes.add(bLimpa);
		botoes.add(bCadastrarTurma);

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
		lblCodigo.setBounds(100, 100, 200, 100);
		lblProfessor.setBounds(100, 150, 200, 100);
		tDisciplina.setBounds(230, 85, 300, 30);
		tCodigo.setBounds(230, 135, 300, 30);
		rolagem.setBounds(230, 185, 500, 225);
	}

	private void acaoLimpar() {
		tDisciplina.setText("");
		tCodigo.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {
		
		final DisciplinaDAO disciplinaDao = new DisciplinaDAO(connection);
		final ProfessorDAO professorDao = new ProfessorDAO(connection);
		final CoordenadorDAO coordenadorDao = new CoordenadorDAO(connection);
		
		Disciplina disciplina = new Disciplina();
		Professor professor;
		Coordenador coordenadorFind;
		
		disciplina.setCodigo(tCodigo.getText());
		disciplina.setNome(tDisciplina.getText());
		
		professor = professorDao.findById(getIdProfessor());
		coordenadorFind = coordenadorDao.findById(coordenador.getId());
		
		disciplina.setProfessor(professor);
		disciplina.setCoordenador(coordenadorFind);
		
		disciplinaDao.saveOrUpdate(disciplina);
		JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");

		//new NavegaView(usuario, connection).setVisible(true);
		//this.dispose();
		
	}
	
	private int getIdProfessor() {
		Matcher matcher = Pattern.compile("\\d+").matcher(tableProfessores.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private void acaoCadastrarTurma() {
		
		new CadastrarTurmaView(connection).setVisible(true);
		this.dispose();
	}
	
	private void criaJTable() {
		tableProfessores = new JList(modelo);
		pesquisar(modelo);
	}

	private void pesquisar(DefaultListModel modelo) {
		
		ProfessorDAO professorDAO = new ProfessorDAO(connection);
		
		for (Professor professor : professorDAO.findAll()) {
			modelo.addElement(new Professor(professor.getId(), professor.getNome()));
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
		} else if (e.getSource().equals(bCadastrarTurma)) {
			this.acaoCadastrarTurma();
		}

	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

}

