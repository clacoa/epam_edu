package com.epam.edu.rentcar.dao;

import java.sql.Connection;

import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.exception.DaoException;

public interface StatusDao <T extends Status> extends GenericRentCarDao<Status>{

	public boolean isExistsStatus(Connection conn, String status) throws DaoException;
	public Status getByStatus(Connection conn, String status) throws DaoException;
}
