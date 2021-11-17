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
import entity.Disciplina;
import entity.Usuario;

public class DisciplinaDAO extends ModelDao<Disciplina> {
	
	public DisciplinaDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Disciplina());
	}

	@Override
	public List<Disciplina> findAll() {
		final List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		super.findAll(rs -> {
			final Disciplina disciplina = (Disciplina) convertResultSet(rs);
			disciplinas.add(disciplina);
			
		});

		return disciplinas;
	}

	@Override
	public Disciplina findById(Integer id) {
		final Disciplina model = new Disciplina();

		super.findById((rs) -> {
			final Disciplina receita = (Disciplina) convertResultSet(rs);

			model.setId(receita.getId());
			model.setNome(receita.getNome());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Disciplina model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" nome, codigo, id_professor, id_coordenador, id_curso");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?,?,?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setString(1, model.getNome());
			prepareStatement.setString(2, model.getCodigo());
			prepareStatement.setString(3, String.valueOf(model.getProfessor().getId()));
			prepareStatement.setString(4, String.valueOf(model.getCoordenador().getId()));
			prepareStatement.setString(5, "0");
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}

	@Override
	public List<Disciplina> findLike(Disciplina model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Disciplina> receitas = new ArrayList<Disciplina>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.findLike(map, rs -> {
			final Disciplina receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Disciplina find(Disciplina model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Disciplina retorno = new Disciplina();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		
		super.findByString(map, rs -> {
			final Disciplina usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setNome(usuario.getNome());
		});
		
		return retorno;
	}

	@Override
	public Disciplina convertResultSet(ResultSet resultSet) {
		final Disciplina model = new Disciplina();
		try {
			model.setId(resultSet.getInt("id"));
			model.setNome(resultSet.getString("nome"));
			model.setCodigo(resultSet.getString("codigo"));
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
}
