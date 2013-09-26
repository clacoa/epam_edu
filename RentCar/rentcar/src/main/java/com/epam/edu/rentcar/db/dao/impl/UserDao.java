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

public class UserDao extends EntityDao<User> {

	@Override
	public String getTableName() {
		return User.TABLE_NAME;
	}

	public User get(Connection conn, Long id) {
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID,
					User.TABLE_NAME, id));
			if (rs.next()) {
				user = new User(id, rs.getString(User.EMAIL),
						rs.getString(User.PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		return user;
	}

	public List<User> getAll(Connection conn) {
		List<User> userList = null;
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_ALL, User.TABLE_NAME));
			userList = new ArrayList<User>();
			while (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL), rs.getString(User.PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
				userList.add(user);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return userList;
	}

	public void saveOrUpdate(Connection conn, User entity) {
		int updateResult;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID,
					User.TABLE_NAME, entity.getId()));
			if (rs.next()) {
				updateResult = st.executeUpdate(String.format(User.UPDATE,
						entity.getEmail(), entity.getPassword(),
						entity.getNickName(), entity.getFirstName(),
						entity.getLastName(), entity.getPassport(),
						entity.getId()));

			} else {
				updateResult = st.executeUpdate(String.format(User.INSERT,
						entity.getEmail(), entity.getPassword(),
						entity.getNickName(), entity.getFirstName(),
						entity.getLastName(), entity.getPassport()));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
	}

	public void saveOrUpdateAll(Connection conn, Collection<User> entities) {
		for (User entity : entities) {
			saveOrUpdate(conn, entity);
		}
	}

	public List<User> findByNamedQuery(Connection conn, String queryName) {
		List<User> userList = null;
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryName);
			userList = new ArrayList<User>();
			while (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL), rs.getString(User.PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
				userList.add(user);
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {

		}
		return userList;
	}

	public User getByEmail(Connection conn, String email) {
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_EMAIL,
					email));
			if (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL), rs.getString(User.PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return user;
	}

	public boolean isExistsEmail(Connection conn, String email) {
		boolean isExists = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_EMAIL,
					email));
			if (rs.next()) {
				isExists = true;
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		return isExists;
	}

	public User getByPassportNumber(Connection conn, String passportNumber) {
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_PASSPORT,
					passportNumber));
			if (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL), rs.getString(User.PASSWORD),
						rs.getString(User.NICKNAME),
						rs.getString(User.FIRSTNAME),
						rs.getString(User.LASTNAME),
						rs.getString(User.PASSPORT));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return user;
	}
	public boolean isExistsPassportNumber(Connection conn, String email) {
		boolean isExists = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_PASSPORT,
					email));
			if (rs.next()) {
				isExists = true;
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		return isExists;
	}

}
