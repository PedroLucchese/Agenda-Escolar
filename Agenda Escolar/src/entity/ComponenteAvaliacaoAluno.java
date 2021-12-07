package entity;

public class ComponenteAvaliacaoAluno extends ModelAbstract{
	private String tableName = "componenteavaliacaoaluno";
	private Integer idComponente;
	private Integer idAluno;
	private Integer idTurma;
	private double valor;
	
	public ComponenteAvaliacaoAluno() {}

	public ComponenteAvaliacaoAluno(int idComponente, int idAluno, int idTurma, double valor) {
		this.setIdComponente(idComponente);
		this.setIdAluno(idAluno);
		this.setIdTurma(idTurma);
		this.setValor(valor);
	}

	public ComponenteAvaliacaoAluno(Integer id, int idComponente, int idAluno, int idTurma, double valor) {
		this.setId(id);
		this.setIdComponente(idComponente);
		this.setIdAluno(idAluno);
		this.setIdTurma(idTurma);
		this.setValor(valor);
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Integer getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	
	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	
	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	
	@Override
	public String toString() {
		return "";
	}
}
