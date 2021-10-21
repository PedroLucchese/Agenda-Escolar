package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import dao.UsuarioDAO;
import entity.Aluno;
import entity.Usuario;

public class AlunoView extends JFrame implements ActionListener {
	Connection connection;

	private JButton bVolta, bCalcularMedia;
	private JPanel fundo, botoes, campos;

	private JTextField tNota1, tNota2, tNota3, tMedia;

	private Aluno aluno;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tNota1 = new JTextField("");
		tNota2 = new JTextField("");
		tNota3 = new JTextField("");
		tMedia = new JTextField("");

		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);

		bCalcularMedia = new JButton("Calcular média");
		bCalcularMedia.addActionListener(this);
		
		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Nota 1:"));
		campos.add(tNota1);
		campos.add(new JLabel("Nota 2:"));
		campos.add(tNota2);
		campos.add(new JLabel("Nota 3:"));
		campos.add(tNota3);
		campos.add(new JLabel("Media:"));
		campos.add(tMedia);

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
	
	private void calcularMedia() {
		Map<String,Double> vars = new HashMap<>();
		String expression = "2*(x+y+z)";
		vars.put("x", Double.parseDouble(tNota1.getText()));
		vars.put("y", Double.parseDouble(tNota2.getText()));
		vars.put("z", Double.parseDouble(tNota3.getText()));
		
		tMedia.setText(avaliar(expression, vars).toString());
		
	}
	
	private Double avaliar(String expression, Map<String,Double> variables) {
	    DoubleEvaluator eval = new DoubleEvaluator();
	    StaticVariableSet<Double> vars = new StaticVariableSet<Double>();
	    
	    for (String name : variables.keySet()) {
			vars.set(name,variables.get(name));
		}
	    Double result = eval.evaluate(expression, vars);
	    return result;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}

