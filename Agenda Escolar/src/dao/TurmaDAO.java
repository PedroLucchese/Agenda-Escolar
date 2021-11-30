package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Professor;
import entity.Turma;

public class TurmaDAO extends ModelDao<Turma> {
	public TurmaDAO(Connection connection) {
		super.setConnection(connection);
		super.setModel(new Turma());
	}

	@Override
	public List<Turma> findAll() {
		final List<Turma> turmas = new ArrayList<Turma>();

		super.findAll(rs -> {
			final Turma turma = (Turma) convertResultSet(rs);
			
			turmas.add(turma);
		});

		return turmas;
	}
	
	public List<Turma> findByProfessorId(int professorId) {
		final List<Turma> turmas = new ArrayList<Turma>();
		final Map<String, Integer> params = new HashMap<String, Integer>();
		
		params.put("id_professor", professorId);
		
		super.findByInt(params, rs -> {
			final Turma turma = (Turma) convertResultSet(rs);
			
			turmas.add(turma);
		});
		
		return turmas;
	}

	@Override
	public Turma findById(Integer id) {
		final Turma model = new Turma();

		super.findById((rs) -> {
			final Turma receita = (Turma) convertResultSet(rs);

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
	public void saveOrUpdate(Turma model) {

		String sql = "insert into " + getModel().getTableName();

		sql = sql.concat(" ( ");
		sql = sql.concat(" nome,horario,id_disciplina,id_professor");

		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");

		sql = sql.concat("?,?,?,?");

		sql = sql.concat(" ) ");

		try {

			final Connection openConnection = super.getConnection();

			final PreparedStatement prepareStatement = openConnection.prepareStatement(sql);
			prepareStatement.setString(1, model.getNome());
			prepareStatement.setString(2, model.getHorario());
			prepareStatement.setString(3, model.getDisciplina().getId().toString());
			prepareStatement.setString(4, model.getProfessor().getId().toString());
			
			prepareStatement.executeUpdate();

			prepareStatement.close();

		} catch (final SQLException se) {
			System.out.println("Não foi poss�vel conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}

	@Override
	public List<Turma> findLike(Turma model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Turma> receitas = new ArrayList<Turma>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.findLike(map, rs -> {
			final Turma receita = convertResultSet(rs);
		});

		return receitas;
	}

	public Turma find(Turma model) {
		final Map<String, String> map = new HashMap<String, String>();
		final Turma retorno = new Turma();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		
		super.findByString(map, rs -> {
			final Turma usuario = convertResultSet(rs);
			retorno.setId(usuario.getId());
			retorno.setNome(usuario.getNome());
		});
		
		return retorno;
	}

	@Override
	public Turma convertResultSet(ResultSet resultSet) {
		final Turma model = new Turma();
		try {

			model.setId(resultSet.getInt("id"));
			model.setNome(resultSet.getString("nome"));

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
}
