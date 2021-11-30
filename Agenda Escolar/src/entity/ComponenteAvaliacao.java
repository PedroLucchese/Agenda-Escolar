package entity;

public class ComponenteAvaliacao extends ModelAbstract{
	private String tableName = "componentesavaliacao";
	private String componente;
	private int peso;
	private int idAvaliacao;
	private int idTurma;
	
	public ComponenteAvaliacao(Integer id, int idAvaliacao) {
		this.setId(id);
		this.setIdAvaliacao(idAvaliacao);
	}
	
	public ComponenteAvaliacao() {}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	
	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", componente=" + componente + ", peso=" + peso + "]";
	}
}
