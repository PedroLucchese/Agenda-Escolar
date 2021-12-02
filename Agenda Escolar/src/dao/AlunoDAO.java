package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Aluno;
import entity.Usuario;

public class AlunoDAO extends ModelDao<Aluno> {

	public AlunoDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Aluno());
	}

	@Override
	public List<Aluno> findAll() {
		final List<Aluno> alunos = new ArrayList<Aluno>();

		super.findAll(rs -> {
			final Aluno aluno = convertResultSet(rs);
			
			if (aluno.getTipo().equals("1")) {
				alunos.add(aluno);
			}
		});

		return alunos;
	}
	
	public List<Aluno> findByTurmaId(Integer idTurma) {
		final List<Aluno> alunos = new ArrayList<Aluno>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_turma", idTurma);
		
		super.findByInt(params, rs -> {
			final Aluno aluno = convertResultSet(rs);
			
			if (aluno.getTipo().equals("1")) {
				alunos.add(aluno);
			}
		});
		
		return alunos;
	}

	@Override
	public Aluno findById(Integer id) {
		final Aluno model = new Aluno();

		super.findById((rs) -> {
			final Aluno receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setNome(receita.getNome());
			model.setSenha(receita.getSenha());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Aluno model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" nome,senha,tipo");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setString(1, model.getNome());
			prepareStatement.setString(2, model.getSenha());
			prepareStatement.setString(3, model.getTipo());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}
	
	public void updateTurma(Aluno model) {
		String sql = "update " + getModel().getTableName() + " SET id_turma = ? WHERE id = ?";
		
		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setInt(1, model.getTurma().getId());
			prepareStatement.setInt(2, model.getId());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	@Override
	public List<Aluno> findLike(Aluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Aluno> receitas = new ArrayList<Aluno>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());

		super.findLike(map, rs -> {
			final Aluno receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Aluno find(Aluno model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Aluno retorno = new Aluno();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());
		map.put("tipo", model.getTipo());
		
		super.findByString(map, rs -> {
			final Aluno usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setNome(usuario.getNome());
			retorno.setSenha(usuario.getSenha());
			retorno.setTipo(usuario.getTipo());
		});
		
		return retorno;
	}

	@Override
	public Aluno convertResultSet(ResultSet resultSet) {
		final Aluno model = new Aluno();
		try {

			model.setId(resultSet.getInt("id"));
			model.setNome(resultSet.getString("nome"));
			model.setSenha(resultSet.getString("senha"));
			model.setTipo(resultSet.getString("tipo"));

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}

