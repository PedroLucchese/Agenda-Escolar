package entity;

import java.util.ArrayList;

public class Turma extends ModelAbstract{
	private String tableName = "turma";
	private String nome;
	private String descricao;
	private String horario; // Horário tá como VARCHAR(5) no banco pq é pra ser salvo como XX-XX. exemplo: 71-72
	private Disciplina disciplina;
	private Professor professor;
	private ArrayList<Integer> idAlunos;

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public ArrayList<Integer> getAlunos() {
		return idAlunos;
	}

	public void setAlunos(ArrayList<Integer> alunos) {
		this.idAlunos = alunos;
	}
	
	public void addAluno(Integer idAluno) {
		this.idAlunos.add(idAluno);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
