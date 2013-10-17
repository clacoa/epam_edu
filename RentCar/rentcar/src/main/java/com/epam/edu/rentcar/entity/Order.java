package com.epam.edu.rentcar.entity;

import java.util.Date;

import org.apache.log4j.Logger;

public class Order extends AbstractEntity {

	private static Logger LOG = Logger.getLogger(Order.class);
	
	private Car car;
	private Date dateFrom;
	private Date dateTo;
	private User user;
	private Double orderCost;
	private Double addCost;
	private Status status;
	
	public Order(){
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addCost == null) ? 0 : addCost.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result
				+ ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result
				+ ((orderCost == null) ? 0 : orderCost.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (addCost == null) {
			if (other.addCost != null)
				return false;
		} else if (!addCost.equals(other.addCost))
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (orderCost == null) {
			if (other.orderCost != null)
				return false;
		} else if (!orderCost.equals(other.orderCost))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
