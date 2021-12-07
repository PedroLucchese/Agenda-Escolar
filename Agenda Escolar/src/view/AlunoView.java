package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import dao.AvaliacaoDAO;
import dao.ComponenteAvaliacaoAlunoDAO;
import dao.ComponenteAvaliacaoDAO;
import dao.TurmaAlunoDAO;
import dao.TurmaDAO;
import dao.UsuarioDAO;
import entity.Aluno;
import entity.ComponenteAvaliacao;
import entity.ComponenteAvaliacaoAluno;
import entity.Turma;
import entity.TurmaAluno;
import entity.Usuario;

public class AlunoView extends JFrame implements ActionListener, ListSelectionListener {
	Connection connection;

	private JButton bVolta, bCalcularMedia;
	private JPanel fundo, botoes, campos;

	private Aluno aluno;
	
	private JScrollPane rolagemTurmas;
	private JList listaTurmas;
	private DefaultListModel modeloTurmas;

	private ArrayList<JLabel> lblNotas;
	private ArrayList<JLabel> lblComponentes;
	private ArrayList<ComponenteAvaliacao > listComponentes;
	
	private ComponenteAvaliacaoDAO componentesAvaliacaoDAO;
	private ComponenteAvaliacaoAlunoDAO componenteAvaliacaoAlunoDAO;
	
	int lblComponenteVar, lblNotaVar;
	
	private void init() {

		this.setTitle("Visualizar notas");
		this.setSize(900, 900);

		lblNotas = new ArrayList<JLabel>();
		lblComponentes = new ArrayList<JLabel>();
		listComponentes = new ArrayList<ComponenteAvaliacao>();
		
		componentesAvaliacaoDAO = new ComponenteAvaliacaoDAO(connection);
		componenteAvaliacaoAlunoDAO = new ComponenteAvaliacaoAlunoDAO(connection);
		
		modeloTurmas = new DefaultListModel();
		criaJListTurmas();
		
		listaTurmas.addListSelectionListener(this);
		
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);

		bCalcularMedia = new JButton("Calcular média");
		bCalcularMedia.addActionListener(this);
		
		campos = new JPanel(new FlowLayout());
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		rolagemTurmas = new JScrollPane(listaTurmas);
		
		campos.setLayout(null);
		
		setComponentsBounds();
		
		campos.add(rolagemTurmas);
		
		botoes.add(bVolta);
		botoes.add(bCalcularMedia);

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
		} else if (e.getSource().equals(bCalcularMedia)) {
			this.calcularMedia();
		}

	}
	
	private void setComponentsBounds() {
		rolagemTurmas.setBounds(230, 5, 500, 225);
	}
	
	private void calcularMedia() {
		Map<String,Double> vars = new HashMap<>();
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(connection);
		
		List<ComponenteAvaliacaoAluno> componenteAvaliacaoAluno = componenteAvaliacaoAlunoDAO.findNotas(listComponentes, aluno.getId(), getIdTurma());
		
		String expression = avaliacaoDAO.findById(listComponentes.get(0).getIdAvaliacao()).getFormula();
		
		for (ComponenteAvaliacaoAluno componenteAvaliacaoAlunoIt : componenteAvaliacaoAluno) {
			
			ComponenteAvaliacao componenteAvaliacao = componentesAvaliacaoDAO.findById(componenteAvaliacaoAlunoIt.getIdComponente());
			
			System.out.println(componenteAvaliacao.getComponente() + componenteAvaliacaoAlunoIt.getValor());
			vars.put(componenteAvaliacao.getComponente(), componenteAvaliacaoAlunoIt.getValor());
			
		}
		
		vars.put("p1", 10.0);
		vars.put("p2", 10.0);
		vars.put("p3", 10.0);
		vars.put("p4", 10.0);
		
		criaLabelsMedia(avaliar(expression, vars));
	}
	
	private void criaLabelsMedia(Double nota) {
		JLabel lblMediaTexto = new JLabel("Média: ");
		JLabel lblMediaValor = new JLabel(String.valueOf(nota));
		
		lblMediaTexto.setBounds(100, lblComponenteVar + 50, 200, 100);
		lblMediaValor.setBounds(230, lblNotaVar + 50, 200, 100);
		
		campos.add(lblMediaTexto);
		campos.add(lblMediaValor);
		lblNotas.add(lblMediaValor);
		lblComponentes.add(lblMediaTexto);
		
		campos.revalidate();
		campos.repaint();
		fundo.add(campos, BorderLayout.CENTER);

		this.getContentPane().add(fundo);
	}
	
	public Double avaliar(String expression, Map<String,Double> variables) {
	    DoubleEvaluator eval = new DoubleEvaluator();
	    StaticVariableSet<Double> vars = new StaticVariableSet<Double>();
	    
	    for (String name : variables.keySet()) {
			vars.set(name,variables.get(name));
		}
	    
	    Double result = eval.evaluate(expression, vars);
	    return result;
	}
	
	private void criaLabels() {
		
		lblComponenteVar = 300;
		lblNotaVar = 325;
		
		for (ComponenteAvaliacao componenteAvaliacao : componentesAvaliacaoDAO.findByTurmaId(getIdTurma())) {
			
			List<ComponenteAvaliacaoAluno> componenteAvaliacaoAluno = componenteAvaliacaoAlunoDAO.findNota(componenteAvaliacao.getId(), aluno.getId(), getIdTurma());
			
			JLabel lblNota;
			
			if (componenteAvaliacaoAluno.size() > 0) {
				lblNota = new JLabel(String.valueOf(componenteAvaliacaoAluno.get(0).getValor()));
			} else {
				lblNota = new JLabel("Sem nota");
			}
			
			JLabel lblComponente = new JLabel("Componente " + componenteAvaliacao.getComponente());
			
			lblComponente.setBounds(100, lblComponenteVar, 200, 100);
			lblNota.setBounds(230, lblNotaVar, 200, 100);
			
			lblComponenteVar += 50;
			lblNotaVar += 50;
			
			campos.add(lblComponente);
			campos.add(lblNota);
			lblComponentes.add(lblComponente);
			lblNotas.add(lblNota);
			listComponentes.add(componenteAvaliacao);
		}
		
		campos.revalidate();
		campos.repaint();
		fundo.add(campos, BorderLayout.CENTER);

		this.getContentPane().add(fundo);
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
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO(connection);
		TurmaDAO turmaDAO = new TurmaDAO(connection);
		
		for (TurmaAluno turmaAluno : turmaAlunoDAO.findByAlunoId(aluno.getId())) {
			Turma turma = turmaDAO.findById(turmaAluno.getIdTurma());
			
			if (turma != null) {
				modelo.addElement(turma);
			}
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	private void limpaLabels() {
		for (JLabel lblComponente: lblComponentes) {
			campos.remove(lblComponente);
		}
		
		for (JLabel lblNota : lblNotas) {
			campos.remove(lblNota);
		}
		
		lblComponentes = new ArrayList<JLabel>();
		lblNotas = new ArrayList<JLabel>();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(listaTurmas)) {
			if (e.getValueIsAdjusting()) {
				limpaLabels();
				criaLabels();
			}
		}
	}

}

