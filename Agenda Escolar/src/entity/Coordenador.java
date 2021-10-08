package entity;

public class Coordenador extends Usuario{
	private String tableName = "coordenador";
	
	
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
		return "Coordenador [tableName=" + tableName + "]";
	}
}