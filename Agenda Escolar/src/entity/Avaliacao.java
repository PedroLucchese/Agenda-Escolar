package entity;

public class Avaliacao extends ModelAbstract{
	private String tableName = "avaliacao";
	private String nome;
	private String descricao;
	private String formula;

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

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nome=" + nome + ", descricao=" + descricao + ", formula=" + formula + "]";
	}
}
