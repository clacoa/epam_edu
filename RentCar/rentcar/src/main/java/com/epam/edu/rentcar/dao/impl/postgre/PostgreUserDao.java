package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.edu.rentcar.dao.impl.UserDao;
import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.entity.User;

public class PostgreUserDao extends PostgreEntityDao<User> implements
		UserDao<User> {

	private final static String UPDATE = "Update users Set email=?, userpassword=?, nickname=?, firstname=?, lastname=?, passportnumber=?, roleid=? where id=?";
	private final static String INSERT = "Insert into users (email, userpassword, nickname, firstname, lastname, passportnumber, roleid) values (?,?,?,?,?,?,?)";
	private final static String GET_BY_COLUMN = "Select * from users where %s=?";

	private final static String TABLE_NAME = "users";
	private final static String ID = "id";
	private final static String EMAIL = "email";
	private final static String PASSWORD = "userpassword";
	private final static String NICKNAME = "nickname";
	private final static String FIRSTNAME = "firstname";
	private final static String LASTNAME = "lastname";
	private final static String PASSPORT = "passportnumber";
	private final static String ROLE_ID = "roleid";
	
	
	private PostgreUserRoleDao pUserRoleDao = new PostgreUserRoleDao();

	@Override
	public String getTableName() {
		return TABLE_NAME;

	}

	public User get(Connection conn, Long id) {
		User user = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_BY_ID, getTableName()));
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Status userRole = pUserRoleDao.get(conn, Long.valueOf(rs.getString(ROLE_ID)));
				user = new User(id, rs.getString(EMAIL),
						rs.getString(PASSWORD), rs.getString(NICKNAME),
						rs.getString(FIRSTNAME), rs.getString(LASTNAME),
						rs.getString(PASSPORT), userRole);
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {
		}
		return user;
	}

	public List<User> getAll(Connection conn) {
		List<User> userList = null;
		User user = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_ALL, getTableName()));
			ResultSet rs = pst.executeQuery();
			userList = new ArrayList<User>();
			while (rs.next()) {
				Status userRole = pUserRoleDao.get(conn, Long.valueOf(rs.getString(ROLE_ID)));
				user = new User(Long.valueOf(rs.getString(ID)),
						rs.getString(EMAIL), rs.getString(PASSWORD),
						rs.getString(NICKNAME), rs.getString(FIRSTNAME),
						rs.getString(LASTNAME), rs.getString(PASSPORT), userRole);
				userList.add(user);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
		}
		return userList;
	}

	public void saveOrUpdate(Connection conn, User entity) {
		int updateResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, entity.getId())) {
				pst = conn.prepareStatement(UPDATE);
				pst.setString(1, entity.getEmail());
				pst.setString(2, entity.getPassword());
				pst.setString(3, entity.getNickName());
				pst.setString(4, entity.getFirstName());
				pst.setString(5, entity.getLastName());
				pst.setString(6, entity.getPassport());
				pst.setLong(7, entity.getRole().getId());
				pst.setLong(8, entity.getId());
				updateResult = pst.executeUpdate();
				pst.close();
			} else {
				pst = conn.prepareStatement(INSERT);
				pst.setString(1, entity.getEmail());
				pst.setString(2, entity.getPassword());
				pst.setString(3, entity.getNickName());
				pst.setString(4, entity.getFirstName());
				pst.setString(5, entity.getLastName());
				pst.setString(6, entity.getPassport());
				pst.setLong(7, entity.getRole().getId());
				updateResult = pst.executeUpdate();
				pst.close();
			}
		} catch (Exception ignore) {
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
				Status userRole = pUserRoleDao.get(conn, Long.valueOf(rs.getString(ROLE_ID)));
				user = new User(Long.valueOf(rs.getString(ID)),
						rs.getString(EMAIL), rs.getString(PASSWORD),
						rs.getString(NICKNAME), rs.getString(FIRSTNAME),
						rs.getString(LASTNAME), rs.getString(PASSPORT), userRole);
				userList.add(user);
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {

		}
		return userList;
	}

	public User getByEmail(Connection conn, String email) {
		User user = getByColumnName(conn, EMAIL, email);
		return user;
	}

	public boolean isExistsEmail(Connection conn, String email) {
		boolean isExists = isExistsByColumnName(conn, EMAIL, email);
		return isExists;
	}

	public User getByPassportNumber(Connection conn, String passportNumber) {
		User user = getByColumnName(conn, PASSPORT, passportNumber);
		return user;
	}

	public boolean isExistsPassportNumber(Connection conn, String passportNumber) {
		boolean isExists = isExistsByColumnName(conn, PASSPORT, passportNumber);
		return isExists;
	}

	private boolean isExistsByColumnName(Connection conn, String column,
			String value) {
		boolean isExists = false;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_BY_COLUMN, column));
			pst.setString(1, value);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {
		}
		return isExists;
	}

	private User getByColumnName(Connection conn, String column, String value) {
		User user = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_BY_COLUMN, column));
			pst.setString(1, value);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Status userRole = pUserRoleDao.get(conn, Long.valueOf(rs.getString(ROLE_ID)));
				user = new User(Long.valueOf(rs.getString(ID)),
						rs.getString(EMAIL), rs.getString(PASSWORD),
						rs.getString(NICKNAME), rs.getString(FIRSTNAME),
						rs.getString(LASTNAME), rs.getString(PASSPORT),userRole);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
		}
		return user;
	}

}
