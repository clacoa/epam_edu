package com.epam.edu.rentcar.db.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.db.dao.GenericDao;
import com.epam.edu.rentcar.db.entity.User;

public class UserDao implements GenericDao<User> {

	public User get(Long id) {
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID, id));
			if (rs.next()) {
				user = new User(id, rs.getString(User.EMAIL),
						rs.getString(User.USER_PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
			}
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}
		return user;
	}

	public List<User> getAll() {
		List<User> userList = null;
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(User.GET_ALL);
			userList = new ArrayList<User>();
			while (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL),
						rs.getString(User.USER_PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),  
						rs.getString(User.PASSPORT));
				userList.add(user);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}
		return userList;
	}

	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub

	}

	public void saveOrUpdateAll(Collection<User> entities) {
		// TODO Auto-generated method stub

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public boolean isExists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> findByNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
