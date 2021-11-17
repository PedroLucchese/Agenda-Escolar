package entity;

public class Disciplina extends ModelAbstract{
	private String tableName = "disciplina";
	private String nome;
	private String codigo;
	private Professor professor;
	private Coordenador coordenador;

	public Disciplina(Integer id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	
	public Disciplina(Integer id, String nome, String codigo) {
		this.setId(id);
		this.setNome(nome);
		this.setCodigo(codigo);
	}

	public Disciplina() {
	}
	
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.getId() + ", Nome: " + this.getNome() + ", Código: " + this.getCodigo();
	}
}
