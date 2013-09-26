package com.epam.edu.rentcar.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.epam.edu.rentcar.util.Property;

public class ConnectionPool {
	private static ConnectionPool instance;
	private DataSource datasource;

	public ConnectionPool() {
		PoolProperties p = new PoolProperties();
		p.setUrl(Property.getProperty("database.url"));		
		p.setDriverClassName(Property.getProperty("database.driver"));
		p.setUsername(Property.getProperty("database.user"));
		p.setPassword(Property.getProperty("database.password"));
//		p.setValidationInterval(30000);
//		p.setTimeBetweenEvictionRunsMillis(30000);
//		p.setMaxActive(100);
//		p.setInitialSize(10);
//		p.setMaxWait(10000);
//		p.setMinEvictableIdleTimeMillis(30000);
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
	
	public static ConnectionPool getInstance(){
		if (instance==null){
			instance=new ConnectionPool();			
		}
		return instance;
	}
}