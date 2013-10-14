package com.epam.edu.rentcar.entity;

import java.util.Date;

import org.apache.log4j.Logger;

public class Car extends AbstractEntity {

	private static Logger LOG = Logger.getLogger(Car.class);

	private Double cost;
	private String mark;
	private String model;
	private String description;
	private Status status;

	public Car() {

	}

	public Car(Long id, String mark, String model, Double cost, Status status) {
		this.id = id;
		this.mark = mark;
		this.model = model;
		this.cost = cost;
		this.status = status;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Car [cost=" + cost + ", mark=" + mark + ", model=" + model
				+ ", description=" + description + ", " + status.toString()
				+ "]";
	}
}
