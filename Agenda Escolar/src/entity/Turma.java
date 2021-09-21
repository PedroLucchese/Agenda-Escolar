package entity;

public class Turma extends ModelAbstract{
	private String tableName = "turma";
	private String nome;
	private String descricao;
	private String horario;
	private String professor;
	private String aluno;

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
	
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nome=" + nome + ", descricao=" + descricao + ", horario=" + horario + ", professor=" + professor +", aluno=" + aluno +"]";
	}
}
