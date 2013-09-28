package com.epam.edu.rentcar.entity;

public class Status extends AbstractEntity{
	
	private String status;

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
