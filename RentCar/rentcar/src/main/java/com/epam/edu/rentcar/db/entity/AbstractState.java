package com.epam.edu.rentcar.db.entity;

public abstract class AbstractState extends AbstractEntity{
	
	public AbstractState(Long id) {
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
