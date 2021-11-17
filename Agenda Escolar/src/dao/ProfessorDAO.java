package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Coordenador;
import entity.Professor;
import entity.Usuario;

public class ProfessorDAO extends ModelDao<Professor> {

	public ProfessorDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Professor());
	}

	@Override
	public List<Professor> findAll() {
		final List<Professor> professores = new ArrayList<Professor>();

		super.findAll(rs -> {
			final Professor professor = (Professor) convertResultSet(rs);
			
			if (professor.getTipo().equals("2")) {
				professores.add(professor);
			}
		});

		return professores;
	}

	@Override
	public Professor findById(Integer id) {
		final Professor model = new Professor();

		super.findById((rs) -> {
			final Professor receita = (Professor) convertResultSet(rs);

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
	public void saveOrUpdate(Professor model) {

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

	@Override
	public List<Professor> findLike(Professor model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Professor> receitas = new ArrayList<Professor>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());

		super.findLike(map, rs -> {
			final Professor receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Professor find(Professor model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Professor retorno = new Professor();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());
		map.put("tipo", model.getTipo());
		
		super.findByString(map, rs -> {
			final Professor usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setNome(usuario.getNome());
			retorno.setSenha(usuario.getSenha());
			retorno.setTipo(usuario.getTipo());
		});
		
		return retorno;
	}

	@Override
	public Professor convertResultSet(ResultSet resultSet) {
		final Professor model = new Professor();
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

