package entity;

public class ComponenteAvaliacao extends ModelAbstract{
	private String tableName = "componenteAvaliacao";
	private String nomeVar;
	private String peso;
	
	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNomeVar() {
		return nomeVar;
	}

	public void setNomeVar(String nome) {
		this.nomeVar = nomeVar;
	}
	
	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nomeVar=" + nomeVar + ", peso=" + peso + "]";
	}
}
