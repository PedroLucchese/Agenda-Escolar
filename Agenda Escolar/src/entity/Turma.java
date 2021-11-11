package entity;

import java.util.ArrayList;

public class Turma extends ModelAbstract{
	private String tableName = "turma";
	private String nome;
	private String descricao;
	private String horario;
	private Professor professor;
	private ArrayList<Aluno> alunos;

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
	
	public ArrayList<Aluno> getAluno() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
