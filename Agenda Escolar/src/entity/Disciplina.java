package entity;

public class Disciplina extends ModelAbstract{
	private String tableName = "disciplina";
	private String nome;
	private int codigo;

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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nome=" + nome + ", codigo=" + codigo + "]";
	}
}
