package com.epam.edu.rentcar.entity;

import org.apache.log4j.Logger;

public class Status extends AbstractEntity{
	
	private static Logger LOG = Logger.getLogger(Status.class);
	
	private String status;
	
	public Status(){
		
	}

	public Status(Long id, String status) {
		super(id);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "status = " + status ;
	}
}
