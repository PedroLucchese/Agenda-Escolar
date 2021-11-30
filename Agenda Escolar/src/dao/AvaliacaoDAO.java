package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Avaliacao;
import entity.ComponenteAvaliacao;
import entity.Coordenador;
import entity.Usuario;

public class AvaliacaoDAO extends ModelDao<Avaliacao> {

	public AvaliacaoDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Avaliacao());
	}

	@Override
	public List<Avaliacao> findAll() {
		final List<Avaliacao> receitas = new ArrayList<Avaliacao>();

		super.findAll(rs -> {
			final Avaliacao receita = (Avaliacao) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public Avaliacao findById(Integer id) {
		final Avaliacao model = new Avaliacao();

		super.findById((rs) -> {
			final Avaliacao receita = (Avaliacao) convertResultSet(rs);

			model.setId(receita.getId());
			model.setFormula(receita.getFormula());
			model.setNumeroComponentes(receita.getNumeroComponentes());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Avaliacao model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" formula,numeroComponentes");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setString(1, model.getFormula());
			prepareStatement.setInt(2, model.getNumeroComponentes());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}

	@Override
	public List<Avaliacao> findLike(Avaliacao model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Avaliacao> receitas = new ArrayList<Avaliacao>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("formula", model.getFormula());
		map.put("numeroComponentes", String.valueOf(model.getNumeroComponentes()));

		super.findLike(map, rs -> {
			final Avaliacao receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Avaliacao find(Avaliacao model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Avaliacao retorno = new Avaliacao();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("formula", model.getFormula());
		map.put("numeroComponentes", String.valueOf(model.getNumeroComponentes()));
		
		super.findByString(map, rs -> {
			final Avaliacao usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setFormula(usuario.getFormula());
			retorno.setNumeroComponentes(usuario.getNumeroComponentes());
		});
		
		return retorno;
	}

	@Override
	public Avaliacao convertResultSet(ResultSet resultSet) {
		final Avaliacao model = new Avaliacao();
		try {

			model.setId(resultSet.getInt("id"));
			model.setFormula(resultSet.getString("formula"));
			model.setNumeroComponentes(resultSet.getInt("numeroComponentes"));

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}

