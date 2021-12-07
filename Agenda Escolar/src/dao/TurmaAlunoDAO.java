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
import entity.Coordenador;
import entity.TurmaAluno;
import entity.Usuario;

public class TurmaAlunoDAO extends ModelDao<TurmaAluno> {

	public TurmaAlunoDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new TurmaAluno());
	}

	@Override
	public List<TurmaAluno> findAll() {
		final List<TurmaAluno> receitas = new ArrayList<TurmaAluno>();

		super.findAll(rs -> {
			final TurmaAluno receita = (TurmaAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}
	
	public List<TurmaAluno> findByTurmaId(int idTurma) {
		final List<TurmaAluno> receitas = new ArrayList<TurmaAluno>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_turma", idTurma);
		
		super.findByInt(params, rs -> {
			final TurmaAluno receita = (TurmaAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}
	
	public List<TurmaAluno> findByAlunoId(int idAluno) {
		final List<TurmaAluno> receitas = new ArrayList<TurmaAluno>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_aluno", idAluno);
		
		super.findByInt(params, rs -> {
			final TurmaAluno receita = (TurmaAluno) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public TurmaAluno findById(Integer id) {
		final TurmaAluno model = new TurmaAluno();

		super.findById((rs) -> {
			final TurmaAluno receita = (TurmaAluno) convertResultSet(rs);

			model.setId(receita.getId());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(TurmaAluno model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" id_turma,id_aluno");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setInt(1, model.getIdTurma());
			prepareStatement.setInt(2, model.getIdAluno());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	@Override
	public List<TurmaAluno> findLike(TurmaAluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<TurmaAluno> receitas = new ArrayList<TurmaAluno>();

		map.put("id", null);
		map.put("id_turma", null);
		map.put("id_aluno", null);
		
		if (model.getId() != null) {
			map.put("id", model.getId().toString());
			map.put("id_turma", model.getIdTurma().toString());
			map.put("id_aluno", model.getIdAluno().toString());
		}


		super.findLike(map, rs -> {
			final TurmaAluno receita = convertResultSet(rs);
		});

		return receitas;
	}

	public TurmaAluno find(TurmaAluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final TurmaAluno retorno = new TurmaAluno();

		map.put("id", null);
		map.put("id_turma", null);
		map.put("id_aluno", null);
		
		if (model.getId() != null) {
			map.put("id", model.getId().toString());
			map.put("id_turma", model.getIdTurma().toString());
			map.put("id_aluno", model.getIdAluno().toString());
		}
		
		super.findByString(map, rs -> {
			final TurmaAluno usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
		});
		
		return retorno;
	}

	@Override
	public TurmaAluno convertResultSet(ResultSet resultSet) {
		final TurmaAluno model = new TurmaAluno();
		try {

			model.setId(resultSet.getInt("id"));
			model.setIdTurma(resultSet.getInt("id_turma"));
			model.setIdAluno(resultSet.getInt("id_aluno"));
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}

