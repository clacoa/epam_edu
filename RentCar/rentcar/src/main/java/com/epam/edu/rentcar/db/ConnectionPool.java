package com.epam.edu.rentcar.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserRoleDao;
import com.epam.edu.rentcar.util.Property;

public class ConnectionPool {

	private static Logger LOG = Logger.getLogger(ConnectionPool.class);

	private static volatile ConnectionPool instance;
	private DataSource datasource;

	public ConnectionPool() {
		PoolProperties p = new PoolProperties();
		p.setUrl(Property.getProperty("database.url"));
		p.setDriverClassName(Property.getProperty("database.driver"));
		p.setUsername(Property.getProperty("database.user"));
		p.setPassword(Property.getProperty("database.password"));
		p.setConnectionProperties("useUnicode=yes;characterEncoding=utf8;");
		p.setInitialSize(10);
		datasource = new DataSource();
		datasource.setPoolProperties(p);
	}

	public Connection getConnection() throws SQLException {
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			throw e;
		}
	}

	public static ConnectionPool getInstance() {
		
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}
}
