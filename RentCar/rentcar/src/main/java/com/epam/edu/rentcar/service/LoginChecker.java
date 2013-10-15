package com.epam.edu.rentcar.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderStatusDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserRoleDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.exception.DaoException;
import com.epam.edu.rentcar.util.DataPreparator;

public class LoginChecker {
	private static Logger LOG = Logger.getLogger(PostgreOrderStatusDao.class);
	private static PostgreUserDao userDao= new PostgreUserDao();


	public static User checkUser(Connection conn, String email) {

		User user = null;
		try {
				if (userDao.isExistsEmail(conn, email)) {
					user = userDao.getByEmail(conn, email);
				}
			} catch (DaoException daoException) {
				LOG.error(daoException);
			}
		return user;
	}

	public static boolean checkLogin(Connection conn, String email, String password) {

		boolean isExist = false;

			try {
				if (userDao.isExistsEmail(conn, email)) {
					User user = userDao.getByEmail(conn, email);
					if ((user.getPassword()).equals(DataPreparator
							.encryptPassword(password))) {
						isExist = true;
					}
				}
			} catch (DaoException daoException) {
				LOG.error(daoException);
			}

		return isExist;
	}

	public static int userRegister(Connection conn, String email, String nickName,
			String password, String passwordRepeat, String firstName,
			String lastName, String passport) {

		int result = 0;
		if (password.equals(passwordRepeat)) {
			
				try {
					if (userDao.isExistsEmail(conn, email)) {
						result = 1;
					} else {
						if (userDao.isExistsPassportNumber(conn, passport)) {
							result = 2;
						} else {
							User user = new User(0L, email,
									DataPreparator.encryptPassword(password),
									nickName, firstName, lastName, passport,
									new PostgreUserRoleDao().getByStatus(conn,
											"USER"));
							userDao.saveOrUpdate(conn, user);
							result = 3;
						}
					}
				} catch (DaoException daoException) {
					LOG.error(daoException);
				}
		}
		return result;
	}
}
