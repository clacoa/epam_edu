package com.epam.edu.rentcar.dao.impl.postgre;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.entity.Status;

public class PostgreUserRoleDao extends PostgreStatusDao<Status> {
	
	private static Logger LOG = Logger.getLogger(PostgreUserRoleDao.class);
	
	private final static String TABLE_NAME = "roles";
	private final static String STATUS = "userrole";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String getColumnName() {
		return STATUS;
	}
}
