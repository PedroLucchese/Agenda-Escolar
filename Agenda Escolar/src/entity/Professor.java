package entity;

public class Professor extends Usuario{
	private String tableName = "professor";

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
		return "Professor [tableName=" + tableName + "]";
	}
		
}
