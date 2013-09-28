package com.epam.edu.rentcar.dao.impl;

import java.sql.Connection;

import com.epam.edu.rentcar.dao.GenericRentCarDao;
import com.epam.edu.rentcar.entity.User;

public interface UserDao<T extends User>  extends GenericRentCarDao<User>{
	
	public boolean isExistsEmail(Connection conn, String email);
	public User getByEmail(Connection conn, String email);
	public boolean isExistsPassportNumber(Connection conn, String email);
	public User getByPassportNumber(Connection conn, String passportNumber);
	
}
