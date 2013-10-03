package com.epam.edu.rentcar.entity;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.util.Property;

public abstract class AbstractEntity {
	
	
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
