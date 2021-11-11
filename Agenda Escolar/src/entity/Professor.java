package entity;

public class Professor extends Usuario{
	private String tableName = "usuario";

	public Professor(Integer id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	public Professor() {
	}

	@Override
	public String getTableName() {
		return tableName;
	}
	
	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.getId() + ", Nome: " + this.getNome();
	}
}