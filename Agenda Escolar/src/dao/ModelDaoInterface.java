package dao;

import java.sql.ResultSet;
import java.util.List;

import entity.ModelAbstract;

public interface ModelDaoInterface<M extends ModelAbstract> {
	public List<M> findAll();

	public M findById(Integer id);

	public List<M> findLike(M model);

	public boolean remove(Integer id);

	public void saveOrUpdate(M model);

	public M convertResultSet(ResultSet resultSet);
}