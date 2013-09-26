package com.epam.edu.rentcar.db.entity;

public abstract class AbstractEntity {
	
	public final static String GET_BY_ID = "Select * from %s where id=%s";
	public final static String DELETE_BY_ID = "Delete from %s where id=%s";
	public final static String GET_ALL = "Select * from %s";
	
	protected Long id;

	
	public AbstractEntity(){
	}
	
		
	public AbstractEntity(Long id){
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
