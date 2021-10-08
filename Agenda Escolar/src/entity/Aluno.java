package entity;

public class Aluno extends Usuario{
	private String tableName = "usuario";
	private String matricula;
	
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

	public void setNome(String matricula) {
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", matricula=" + matricula + "]";
	}
}
