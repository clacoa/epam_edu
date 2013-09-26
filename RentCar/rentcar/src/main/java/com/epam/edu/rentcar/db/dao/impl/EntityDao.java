package com.epam.edu.rentcar.db.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.epam.edu.rentcar.db.dao.GenericDao;
import com.epam.edu.rentcar.db.entity.AbstractEntity;

public abstract class EntityDao<T extends AbstractEntity> implements GenericDao<T> {

	public boolean isExists(Connection conn, Long id) {
		boolean isExists = false;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(AbstractEntity.GET_BY_ID, getTableName(), id));
			if (rs.next()) {
				isExists = true;
				rs.close();
				st.close();
			}
		} catch (SQLException ignore) {
		}
		
		
		return isExists;
	}
	
	public void delete(Connection conn, Long id) {
	int executeResult;
	try {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(String.format(AbstractEntity.GET_BY_ID, getTableName(), id));
		if (rs.next()) {
			executeResult = st.executeUpdate(String.format(
					AbstractEntity.DELETE_BY_ID, getTableName(), id));
		}
		rs.close();
		st.close();
	} catch (Exception ingore) {
	}
}

	public String getTableName() {
		return null;
	}
	
}
