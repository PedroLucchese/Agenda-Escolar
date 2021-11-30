package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

import dao.AvaliacaoDAO;
import dao.ComponenteAvaliacaoDAO;
import dao.DisciplinaDAO;
import dao.TurmaDAO;
import entity.Avaliacao;
import entity.ComponenteAvaliacao;
import entity.Professor;
import entity.Turma;

public class ModoAvaliacaoView extends JFrame implements ActionListener {
	Connection connection;
	
	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;
	
	private JScrollPane rolagemTurmas, rolagemModoAvaliacao;
	private JList listTurmas, listModoAvaliacao;
	private DefaultListModel modeloTurmas, modeloModoAvaliacao;
	
	private JLabel lblTurmas, lblModoAvaliacao;
	
	private Professor professor;
	
	public ModoAvaliacaoView(Connection connection, Professor professor) {
		this.professor = professor;
		this.connection = connection;

		this.init();
	}
	
	private void init() {
		
		this.setTitle("Cadastro de modo de avaliação");
		this.setSize(900, 500);
		
		modeloTurmas = new DefaultListModel();
		modeloModoAvaliacao = new DefaultListModel();
		criaJListTurmas();
		criaJListModoAvaliacao();
		
		
		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);
		
		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		lblTurmas = new JLabel("Turma:");
		lblModoAvaliacao = new JLabel("Modo de avaliação:");
		
		rolagemTurmas = new JScrollPane(listTurmas);
		rolagemModoAvaliacao = new JScrollPane(listModoAvaliacao);
		
		campos.setLayout(null);
		
		setComponentsBounds();
		
		campos.add(lblTurmas);
		campos.add(lblModoAvaliacao);
		campos.add(rolagemModoAvaliacao);
		campos.add(rolagemTurmas);
		
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
	
	private void acaoCadastra() {
		final ComponenteAvaliacaoDAO componenteAvaliacaoDAO = new ComponenteAvaliacaoDAO(connection);
		final AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(connection);
		
		ComponenteAvaliacao componenteAvaliacao = new ComponenteAvaliacao();
		Avaliacao avaliacao;
		
		avaliacao = avaliacaoDAO.findById(componenteAvaliacao.getIdAvaliacao());
		
		for (int i = 1; i < avaliacao.getNumeroComponentes() + 1; i++) {
			
			componenteAvaliacao.setIdTurma(getIdTurma());
			componenteAvaliacao.setIdAvaliacao(getIdAvaliacao());
			componenteAvaliacao.setComponente("n" + i);
			componenteAvaliacao.setPeso(10);
			componenteAvaliacaoDAO.saveOrUpdate(componenteAvaliacao);
		}
		
		JOptionPane.showMessageDialog(this, "Modo de avaliação cadastrado com sucesso!");
	}
	
	private void acaoLimpar() {
	}

	private void acaoVoltar() {
		new InicialView(connection).setVisible(true);
		this.dispose();
	}
	
	private void criaJListTurmas() {
		listTurmas = new JList(modeloTurmas);
		pesquisarTurmas(modeloTurmas);
	}
	
	private void pesquisarTurmas(DefaultListModel modelo) {
		TurmaDAO turmaDAO = new TurmaDAO(connection);
		
		for (Turma turma : turmaDAO.findByProfessorId(professor.getId())) {
			modelo.addElement(new Turma(turma.getId(), turma.getNome()));
		}
	}
	
	private int getIdTurma() {
		Matcher matcher = Pattern.compile("\\d+").matcher(listTurmas.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private void criaJListModoAvaliacao() {
		listModoAvaliacao = new JList(modeloModoAvaliacao);
		pesquisarModoAvaliacao(modeloModoAvaliacao);
	}
	
	private void pesquisarModoAvaliacao(DefaultListModel modelo) {
		AvaliacaoDAO modoAvaliacaoDAO = new AvaliacaoDAO(connection);
		
		for (Avaliacao modoAvaliacao : modoAvaliacaoDAO.findAll()) {
			modelo.addElement(new Avaliacao(modoAvaliacao.getId(), modoAvaliacao.getFormula()));
		}
	}
	
	private int getIdAvaliacao() {
		Matcher matcher = Pattern.compile("\\d+").matcher(listModoAvaliacao.getSelectedValue().toString());
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private void setComponentsBounds() {
		lblTurmas.setBounds(100, 50, 200, 100);
		lblModoAvaliacao.setBounds(100, 250, 200, 100);
		//lblProfessor.setBounds(100, 150, 200, 100);
		//tDisciplina.setBounds(230, 85, 300, 30);
		rolagemTurmas.setBounds(230, 10, 350, 200);
		rolagemModoAvaliacao.setBounds(230, 225, 350, 200);
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
}