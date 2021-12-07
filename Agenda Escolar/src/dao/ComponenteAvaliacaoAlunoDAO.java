package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ComponenteAvaliacao;
import entity.ComponenteAvaliacaoAluno;
import entity.Coordenador;
import entity.Usuario;

public class ComponenteAvaliacaoAlunoDAO extends ModelDao<ComponenteAvaliacaoAluno> {

	public ComponenteAvaliacaoAlunoDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new ComponenteAvaliacaoAluno());
	}

	@Override
	public List<ComponenteAvaliacaoAluno> findAll() {
		final List<ComponenteAvaliacaoAluno> receitas = new ArrayList<ComponenteAvaliacaoAluno>();

		super.findAll(rs -> {
			final ComponenteAvaliacaoAluno receita = (ComponenteAvaliacaoAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}

	public List<ComponenteAvaliacaoAluno> findByTurmaId(int idTurma) {
		final List<ComponenteAvaliacaoAluno> receitas = new ArrayList<ComponenteAvaliacaoAluno>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_turma", idTurma);
		
		super.findByInt(params, rs -> {
			final ComponenteAvaliacaoAluno receita = (ComponenteAvaliacaoAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}
	
	public List<ComponenteAvaliacaoAluno> findNotas(ArrayList<ComponenteAvaliacao> componentes, Integer idAluno, int idTurma) {
		final List<ComponenteAvaliacaoAluno> receitas = new ArrayList<ComponenteAvaliacaoAluno>();
		
		for (ComponenteAvaliacao componente : componentes) {
			final Map<String, Integer> params = new HashMap<String, Integer>();
			
			params.put("id_componente", componente.getId());
			params.put(" id_aluno", idAluno);
			params.put(" id_turma", idTurma);
			
			super.findByInt(params, rs -> {
				final ComponenteAvaliacaoAluno receita = (ComponenteAvaliacaoAluno) convertResultSet(rs);
				
				receitas.add(receita);
			});
		}

		return receitas;
	}
	
	public List<ComponenteAvaliacaoAluno> findNota(Integer idComponente, Integer idAluno, int idTurma) {
		final List<ComponenteAvaliacaoAluno> receitas = new ArrayList<ComponenteAvaliacaoAluno>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_componente", idComponente);
		params.put(" id_aluno", idAluno);
		params.put(" id_turma", idTurma);
		
		super.findByInt(params, rs -> {
			final ComponenteAvaliacaoAluno receita = (ComponenteAvaliacaoAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}
	
	@Override
	public ComponenteAvaliacaoAluno findById(Integer id) {
		final ComponenteAvaliacaoAluno model = new ComponenteAvaliacaoAluno();

		super.findById((rs) -> {
			final ComponenteAvaliacaoAluno receita = (ComponenteAvaliacaoAluno) convertResultSet(rs);

			model.setId(receita.getId());
			model.setIdAluno(receita.getIdAluno());
			model.setIdComponente(receita.getIdComponente());
			model.setIdTurma(receita.getIdTurma());
			model.setValor(receita.getValor());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(ComponenteAvaliacaoAluno model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" id_componente,id_aluno,id_turma,valor");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?,?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setInt(1, model.getIdComponente());
			prepareStatement.setInt(2, model.getIdAluno());
			prepareStatement.setInt(3, model.getIdTurma());
			prepareStatement.setDouble(4, model.getValor());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	@Override
	public List<ComponenteAvaliacaoAluno> findLike(ComponenteAvaliacaoAluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<ComponenteAvaliacaoAluno> receitas = new ArrayList<ComponenteAvaliacaoAluno>();

		map.put("id", null);
		
		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("id_componente", model.getIdComponente().toString());
		map.put("id_aluno", model.getIdAluno().toString());
		map.put("id_turma", model.getIdTurma().toString());
		map.put("valor", Double.toString(model.getValor()));

		super.findLike(map, rs -> {
			final ComponenteAvaliacaoAluno receita = convertResultSet(rs);
		});

		return receitas;
	}

	public ComponenteAvaliacaoAluno find(ComponenteAvaliacaoAluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final ComponenteAvaliacaoAluno retorno = new ComponenteAvaliacaoAluno();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("id_componente", model.getIdComponente().toString());
		map.put("id_aluno", model.getIdAluno().toString());
		map.put("id_turma", model.getIdTurma().toString());
		map.put("valor", Double.toString(model.getValor()));
		
		super.findByString(map, rs -> {
			final ComponenteAvaliacaoAluno usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setIdComponente(model.getIdComponente());
			retorno.setIdAluno(model.getIdAluno());
			retorno.setIdTurma(model.getIdTurma());
			retorno.setValor(model.getValor());
		});
		
		return retorno;
	}

	@Override
	public ComponenteAvaliacaoAluno convertResultSet(ResultSet resultSet) {
		final ComponenteAvaliacaoAluno model = new ComponenteAvaliacaoAluno();
		try {
			model.setId(resultSet.getInt("id"));
			model.setIdComponente(resultSet.getInt("id_componente"));
			model.setIdAluno(resultSet.getInt("id_aluno"));
			model.setIdTurma(resultSet.getInt("id_turma"));
			model.setValor(resultSet.getDouble("valor"));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
}