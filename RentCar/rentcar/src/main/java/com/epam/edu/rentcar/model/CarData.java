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

@Override
public String toString() {
	return "CarData [id=" + id + ", modelName=" + modelName + ", description="
			+ description + ", cost=" + cost + ", availableDate="
			+ availableDate + ", status=" + status + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((availableDate == null) ? 0 : availableDate.hashCode());
	result = prime * result + ((cost == null) ? 0 : cost.hashCode());
	result = prime * result
			+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CarData other = (CarData) obj;
	if (availableDate == null) {
		if (other.availableDate != null)
			return false;
	} else if (!availableDate.equals(other.availableDate))
		return false;
	if (cost == null) {
		if (other.cost != null)
			return false;
	} else if (!cost.equals(other.cost))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (modelName == null) {
		if (other.modelName != null)
			return false;
	} else if (!modelName.equals(other.modelName))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	return true;
}



}
