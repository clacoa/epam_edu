package com.epam.edu.rentcar.dao.impl.postgre;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.entity.Status;

public class PostgreCarStatusDao extends PostgreStatusDao<Status> {
	
	private static Logger LOG = Logger.getLogger(PostgreCarStatusDao.class);
	
	private final static String TABLE_NAME = "car_status";
	private final static String STATUS = "carstatus";
	
	
	@Override
	public String getTableName() {
		return TABLE_NAME;

	}
	
	@Override
	public String getColumnName() {
		return STATUS;
	}
}
