package com.epam.edu.rentcar.db.dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.epam.edu.rentcar.db.entity.AbstractEntity;

public interface GenericDao<T extends AbstractEntity> {

	public T get(Connection conn, Long id);

	public List<T> getAll(Connection conn);

	public void saveOrUpdate(Connection conn, T entity);

	public void saveOrUpdateAll(Connection conn, Collection<T> entities);

	public void delete(Connection conn, Long id);

	public boolean isExists(Connection conn, Long id);
	
	public String getTableName();

	public List<T> findByNamedQuery(Connection conn, String queryName);
	
	//public List<T> findByNamedQueryAndNamedParam(Connection conn, String queryName, String paramName, Object value);

}
