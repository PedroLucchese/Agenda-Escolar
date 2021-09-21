package entity;

public class Nota extends ModelAbstract{
	private String tableName = "nota";
	private float nota;

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nota=" + nota + "]";
	}
}
