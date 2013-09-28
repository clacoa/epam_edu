package com.epam.edu.rentcar.dao.impl.postgre;

import com.epam.edu.rentcar.entity.Status;

public class PostgreCarStatusDao extends PostgreStatusDao<Status> {
	
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
