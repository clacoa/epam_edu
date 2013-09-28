package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import com.epam.edu.rentcar.dao.GenericRentCarDao;
import com.epam.edu.rentcar.entity.AbstractEntity;

public abstract class PostgreEntityDao<T extends AbstractEntity> implements
		GenericRentCarDao<T> {

	public final static String GET_BY_ID = "Select * from %s where id=?";
	public final static String DELETE_BY_ID = "Delete from %s where id=?";
	public final static String GET_ALL = "Select * from %s";

	public boolean isExists(Connection conn, Long id) {
		boolean isExists = false;
		try {	
			PreparedStatement pst = conn.prepareStatement(String.format(GET_BY_ID, getTableName()));
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				isExists = true;
				rs.close();
				pst.close();
			}
		} catch (SQLException ignore) {
		}

		return isExists;
	}

	public void delete(Connection conn, Long id) {
		int executeResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, id)) {
				pst = conn.prepareStatement(String.format(DELETE_BY_ID, getTableName()));
				pst.setLong(1, id);
				pst.executeUpdate();
				pst.close();
			}

		} catch (Exception ingore) {
		}
	}

	public void saveOrUpdateAll(Connection conn, Collection<T> entities) {
		for (T entity : entities) {
			saveOrUpdate(conn, entity);
		}
	}

	public String getTableName() {
		return "";
	}

}
