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
						rs.getString(User.PASSWORD),
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
						rs.getString(User.PASSWORD),
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
		Connection conn = null;
		int updateResult;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID,
					entity.getId()));
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
			conn.close();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}

	}

	public void saveOrUpdateAll(Collection<User> entities) {
		for (User entity : entities) {
			saveOrUpdate(entity);
		}

	}

	public void delete(Long id) {
		Connection conn = null;
		int executeResult;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID, id));
			if (rs.next()) {
				executeResult = st.executeUpdate(String.format(
						User.DELETE_BY_ID, id));
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
	}

	public List<User> findByNamedQuery(String queryName) {
		List<User> userList = null;
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryName);
			userList = new ArrayList<User>();
			while (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)),
						rs.getString(User.EMAIL),
						rs.getString(User.PASSWORD),
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

	public List<User> findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isExists(Long id) {
		boolean isExists = false;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_ID, id));
			if (rs.next()) {
				isExists = true;
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
		return isExists;
	}

	public User getByEmail(String email) {
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_EMAIL,
					email));
			if (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.EMAIL)), email,
						rs.getString(User.PASSWORD),
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

	public boolean isExistsEmail(String email) {
		boolean isExists = false;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_EMAIL,
					email));
			if (rs.next()) {
				isExists = true;
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
		return isExists;
	}
	
	public User getByPassportNumber(String passportNumber) {
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(User.GET_BY_PASSPORT,
					passportNumber));
			if (rs.next()) {
				user = new User(Long.valueOf(rs.getString(User.ID)), rs.getString(User.EMAIL),
						rs.getString(User.PASSWORD),
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
}
