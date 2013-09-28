package com.epam.edu.rentcar.dao.impl;

import java.sql.Connection;

import com.epam.edu.rentcar.dao.GenericRentCarDao;
import com.epam.edu.rentcar.entity.Status;

public interface StatusDao <T extends Status> extends GenericRentCarDao<Status>{

	public boolean isExistsStatus(Connection conn, String status);
	public Status getByStatus(Connection conn, String status);
}
