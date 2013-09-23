package com.epam.edu.rentcar.db.dao;

import java.util.Collection;
import java.util.List;

public interface GenericDao<T> {

	public T get(Long id);

	public List<T> getAll();

	public void saveOrUpdate(T entity);

	public void saveOrUpdateAll(Collection<T> entities);

	public void delete(Long id);

	public boolean isExists(Long id);

	public List<T> findByNamedQuery(String queryName);
	
	public List<T> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value);

}
