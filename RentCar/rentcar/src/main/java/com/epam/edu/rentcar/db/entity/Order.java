package com.epam.edu.rentcar.db.entity;

import java.util.Date;

public class Order {

	private Long carID;
	private Date dateFrom;
	private Date dateTo;
	private Long userId;
	private Double cost;
	private Double addCost;
	private OrderState state;

		
	public Long getCarID() {
		return carID;
	}
	public void setCarID(Long carID) {
		this.carID = carID;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getAddCost() {
		return addCost;
	}
	public void setAddCost(Double addCost) {
		this.addCost = addCost;
	}
	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	
}
