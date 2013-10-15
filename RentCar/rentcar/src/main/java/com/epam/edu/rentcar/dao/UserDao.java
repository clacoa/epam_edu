package com.epam.edu.rentcar.dao;

import java.sql.Connection;

import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.exception.DaoException;

public interface UserDao<T extends User>  extends GenericRentCarDao<User>{
	
	public boolean isExistsEmail(Connection conn, String email) throws DaoException;
	public User getByEmail(Connection conn, String email) throws DaoException;
	public boolean isExistsPassportNumber(Connection conn, String email) throws DaoException;
	public User getByPassportNumber(Connection conn, String passportNumber) throws DaoException;
	
}
