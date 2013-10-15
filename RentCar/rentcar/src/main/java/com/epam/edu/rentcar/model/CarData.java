package com.epam.edu.rentcar.model;

import java.util.Date;

import com.epam.edu.rentcar.entity.Car;


public class CarData {
private Long id;
private String modelName;
private String description;
private Double cost;
private Date availableDate;
private Long status;
private String action;

public CarData(){
	
}

public CarData(Long id, String modelName, Double cost, Long status) {
	super();
	this.id = id;
	this.modelName = modelName;
	this.cost = cost;
	this.status = status;
}
public CarData(Car car){
	this.id = car.getId();
	this.modelName = car.getMark()+" "+car.getModel();
	this.cost = car.getCost();
	this.status = car.getStatus().getId();
	this.description=car.getDescription();
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getModelName() {
	return modelName;
}
public void setModelName(String modelName) {
	this.modelName = modelName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Double getCost() {
	return cost;
}
public void setCost(Double cost) {
	this.cost = cost;
}
public Date getAvailableDate() {
	return availableDate;
}
public void setAvailableDate(Date availableDate) {
	this.availableDate = availableDate;
}
public Long getStatus() {
	return status;
}
public void setStatus(Long status) {
	this.status = status;
}

public String getAction() {
	return action;
}

public void setAction(String action) {
	this.action = action;
}
}
