package entity;

public class Aluno extends Usuario{
	private String tableName = "usuario";
	private String matricula;
	private Turma turma;
	
	public Aluno(Integer id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	public Aluno() {
	}
	
	@Override
	public String getTableName() {
		return tableName;
	}
	
	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.getId() + ", Nome: " + this.getNome();
	}
}
