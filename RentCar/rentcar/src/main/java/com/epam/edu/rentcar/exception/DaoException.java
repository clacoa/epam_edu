package com.epam.edu.rentcar.exception;

public class DaoException extends Exception {

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message,Throwable t) {		
		super("Error on " + message + " DAO", t);
	}
}
