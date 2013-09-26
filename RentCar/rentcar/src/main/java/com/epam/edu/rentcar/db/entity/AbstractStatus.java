package com.epam.edu.rentcar.db.entity;

public abstract class AbstractStatus extends AbstractEntity{
	
	public AbstractStatus(Long id) {
		super(id);
	}

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
