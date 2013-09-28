package com.epam.edu.rentcar.dao.impl.postgre;

import com.epam.edu.rentcar.entity.Status;

public class PostgreOrderStatusDao extends PostgreStatusDao<Status> {
	
	private final static String TABLE_NAME = "order_status";
	private final static String STATUS = "orderstatus";
	
	@Override
	public String getTableName() {
		return TABLE_NAME;

	}
	@Override
	public String getColumnName() {
		return STATUS;
	}
}
