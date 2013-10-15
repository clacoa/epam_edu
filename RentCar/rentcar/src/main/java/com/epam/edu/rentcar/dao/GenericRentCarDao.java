package com.epam.edu.rentcar.dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.epam.edu.rentcar.entity.AbstractEntity;
import com.epam.edu.rentcar.exception.DaoException;

public interface GenericRentCarDao<T extends AbstractEntity> {

	public T get(Connection conn, Long id) throws DaoException;

	public List<T> getAll(Connection conn) throws DaoException;

	public void saveOrUpdate(Connection conn, T entity) throws DaoException;

	public void saveOrUpdateAll(Connection conn, Collection<T> entities) throws DaoException;

	public void delete(Connection conn, Long id) throws DaoException;

	public boolean isExists(Connection conn, Long id) throws DaoException;
	
	public List<T> findByNamedQuery(Connection conn, String queryName) throws DaoException;
	
	public String getTableName();
	
	//public List<T> findByNamedQueryAndNamedParam(Connection conn, String queryName, String paramName, Object value);

}
