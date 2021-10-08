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
import entity.Usuario;

public class CoordenadorDAO extends ModelDao<Coordenador> {

	public CoordenadorDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Coordenador());
	}

	@Override
	public List<Coordenador> findAll() {
		final List<Coordenador> receitas = new ArrayList<Coordenador>();

		super.findAll(rs -> {
			final Coordenador receita = (Coordenador) convertResultSet(rs);
			

		});

		return receitas;
	}

	@Override
	public Coordenador findById(Integer id) {
		final Coordenador model = new Coordenador();

		super.findById((rs) -> {
			final Coordenador receita = (Coordenador) convertResultSet(rs);

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
	public void saveOrUpdate(Coordenador model) {

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
	public List<Coordenador> findLike(Coordenador model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Coordenador> receitas = new ArrayList<Coordenador>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());

		super.findLike(map, rs -> {
			final Coordenador receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Coordenador find(Coordenador model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Coordenador retorno = new Coordenador();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("senha", model.getSenha());
		map.put("tipo", model.getTipo());
		
		super.findByString(map, rs -> {
			final Coordenador usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setNome(usuario.getNome());
			retorno.setSenha(usuario.getSenha());
			retorno.setTipo(usuario.getTipo());
		});
		
		return retorno;
	}

	@Override
	public Coordenador convertResultSet(ResultSet resultSet) {
		final Coordenador model = new Coordenador();
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

