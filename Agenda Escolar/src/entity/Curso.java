package entity;

public class Curso extends ModelAbstract{
	private String tableName = "curso";
	private String nome;
	private String descricao;
	private int coordenador;
	private String disciplina;
	
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

	public int getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(int coordenador) {
		this.coordenador = coordenador;
	}
	
	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nome=" + nome + ", descricao=" + descricao + ", coordenador=" + coordenador + ", disciplina=" + disciplina +"]";
	}
}
