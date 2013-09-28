package com.epam.edu.rentcar.entity;

import java.util.Date;

public class Order extends AbstractEntity {

	//private Long carID;
	private Car car;
	private Date dateFrom;
	private Date dateTo;
	private User user;
	private Double orderCost;
	private Double addCost;
	private Status status;

	public Order(Long id, User user, Car car, Date dateFrom, Date dateTo,
			Double orderCost, Status status) {
		this.id = id;
		this.setUser(user);
		this.car = car;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.orderCost = orderCost;
		this.status=status;
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

	public Double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Double cost) {
		this.orderCost = cost;
	}

	public Double getAddCost() {
		return addCost;
	}

	public void setAddCost(Double addCost) {
		this.addCost = addCost;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [" + car.toString() + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + ", " + user.toString() + ", orderCost=" + orderCost
				+ ", addCost=" + addCost + ", status=" + status + "]";
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
