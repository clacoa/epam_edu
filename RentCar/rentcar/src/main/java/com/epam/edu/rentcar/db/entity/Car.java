package com.epam.edu.rentcar.db.entity;

public class Car {
	
	private Double cost;
	private String mark;
	private String model;
	private String description;
	private Long idState;
	private CarState state;
	
	
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getIdState() {
		return idState;
	}
	public void setIdState(Long idState) {
		this.idState = idState;
	}
	public CarState getState() {
		return state;
	}
	public void setState(CarState state) {
		this.state = state;
	}

}
