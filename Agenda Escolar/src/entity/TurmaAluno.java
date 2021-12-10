package entity;

public class TurmaAluno extends ModelAbstract{
	private String tableName = "turmaaluno";
	private int idTurma;
	private int idAluno;
	
	public TurmaAluno(int idTurma, int idAluno) {
		this.setIdTurma(idTurma);
		this.setIdAluno(idAluno);
	}
	
	public TurmaAluno() {}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	
	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	
	@Override
	public String toString() {
		return "";
	}
}
