package entity;

public class Avaliacao extends ModelAbstract{
	private String tableName = "avaliacao";
	private String formula;
	private int nroComponentes;

	public Avaliacao(Integer id, String formula) {
		this.setId(id);
		this.setFormula(formula);
	}
	
	public Avaliacao() {}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getNumeroComponentes() {
		return nroComponentes;
	}

	public void setNumeroComponentes(int nroComponentes) {
		this.nroComponentes = nroComponentes;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	@Override
	public String toString() {
		return "Id: " + this.getId() + ", " + this.getFormula();
	}
}
