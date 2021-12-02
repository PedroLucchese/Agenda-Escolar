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
import entity.Usuario;

public class ComponenteAvaliacaoDAO extends ModelDao<ComponenteAvaliacao> {

	public ComponenteAvaliacaoDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new ComponenteAvaliacao());
	}

	@Override
	public List<ComponenteAvaliacao> findAll() {
		final List<ComponenteAvaliacao> receitas = new ArrayList<ComponenteAvaliacao>();

		super.findAll(rs -> {
			final ComponenteAvaliacao receita = (ComponenteAvaliacao) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}

	public List<ComponenteAvaliacao> findByTurmaId(int idTurma) {
		final List<ComponenteAvaliacao> receitas = new ArrayList<ComponenteAvaliacao>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_turma", idTurma);
		
		super.findByInt(params, rs -> {
			final ComponenteAvaliacao receita = (ComponenteAvaliacao) convertResultSet(rs);
			
			receitas.add(receita);
		});

		return receitas;
	}
	
	@Override
	public ComponenteAvaliacao findById(Integer id) {
		final ComponenteAvaliacao model = new ComponenteAvaliacao();

		super.findById((rs) -> {
			final ComponenteAvaliacao receita = (ComponenteAvaliacao) convertResultSet(rs);

			model.setId(receita.getId());
			model.setIdAvaliacao(receita.getIdAvaliacao());
			model.setComponente(receita.getComponente());
			model.setPeso(receita.getPeso());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(ComponenteAvaliacao model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" id_avaliacao,componente,peso,id_turma");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?,?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setInt(1, model.getIdAvaliacao());
			prepareStatement.setString(2, model.getComponente());
			prepareStatement.setInt(3, model.getPeso());
			prepareStatement.setInt(4, model.getIdTurma());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	@Override
	public List<ComponenteAvaliacao> findLike(ComponenteAvaliacao model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<ComponenteAvaliacao> receitas = new ArrayList<ComponenteAvaliacao>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("id_avaliacao", String.valueOf(model.getIdAvaliacao()));
		map.put("componente", model.getComponente());
		map.put("peso", Integer.toString(model.getPeso()));

		super.findLike(map, rs -> {
			final ComponenteAvaliacao receita = convertResultSet(rs);
		});

		return receitas;
	}

	public ComponenteAvaliacao find(ComponenteAvaliacao model) {
		final Map<String, String> map = new HashMap<String, String>();
		final ComponenteAvaliacao retorno = new ComponenteAvaliacao();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("id_avaliacao", String.valueOf(model.getIdAvaliacao()));
		map.put("componente", model.getComponente());
		map.put("peso", Integer.toString(model.getPeso()));
		
		super.findByString(map, rs -> {
			final ComponenteAvaliacao usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setIdAvaliacao(model.getIdAvaliacao());
			retorno.setComponente(model.getComponente());
			retorno.setPeso(model.getPeso());
		});
		
		return retorno;
	}

	@Override
	public ComponenteAvaliacao convertResultSet(ResultSet resultSet) {
		final ComponenteAvaliacao model = new ComponenteAvaliacao();
		try {

			model.setId(resultSet.getInt("id"));
			model.setIdAvaliacao(resultSet.getInt("id_avaliacao"));
			model.setComponente(resultSet.getString("componente"));
			model.setPeso(resultSet.getInt("peso"));

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}

