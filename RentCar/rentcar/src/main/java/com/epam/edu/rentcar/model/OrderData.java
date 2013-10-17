package com.epam.edu.rentcar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.impl.SearchCarCommand;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.User;

public class OrderData {

	private static Logger LOG = Logger.getLogger(OrderData.class);

	private CarData carData;
	private Date dateFrom;
	private Date dateTo;
	private Double orderCost;
	private Long status;

	private SimpleDateFormat formatRU = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat formatEN = new SimpleDateFormat("MM/dd/yyyy");

	public CarData getCarData() {
		return carData;
	}

	public void setCarData(CarData carData) {
		this.carData = carData;
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

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getFormatedDateFrom(String language) {
		if (language.equals("ru")) {
			return formatRU.format(dateFrom);
		} else {
			return formatEN.format(dateFrom);
		}
	}

	public String getFormatedDateTo(String language) {
		if (language.equals("ru")) {
			return formatRU.format(dateTo);
		} else {
			return formatEN.format(dateTo);
		}
	}

	public void setFormatedDateFrom(String language, String dateFrom) {
		try {
			if (language.equals("ru")) {
				this.dateFrom = formatRU.parse(dateFrom);
			} else {
				this.dateFrom = formatEN.parse(dateFrom);
			}
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void setFormatedDateTo(String language, String dateTo) {
		try {
			if (language.equals("ru")) {
				this.dateTo = formatRU.parse(dateTo);
			} else {
				this.dateTo = formatEN.parse(dateTo);
			}
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public boolean isFilled() {
		return this.getCarData() != null && this.getDateFrom() != null
				&& this.getDateTo() != null && this.getOrderCost() != null;

	}

	@Override
	public String toString() {
		return "OrderData [carData=" + carData + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + ", orderCost=" + orderCost
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carData == null) ? 0 : carData.hashCode());
		result = prime * result
				+ ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result
				+ ((orderCost == null) ? 0 : orderCost.hashCode());
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
		OrderData other = (OrderData) obj;
		if (carData == null) {
			if (other.carData != null)
				return false;
		} else if (!carData.equals(other.carData))
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
		return true;
	}
	
	
}
