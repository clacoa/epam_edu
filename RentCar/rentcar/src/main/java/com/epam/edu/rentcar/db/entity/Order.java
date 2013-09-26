package com.epam.edu.rentcar.db.entity;

import java.util.Date;

public class Order extends AbstractEntity {

	public final static String UPDATE = "Update orders Set userid=%s, carid=%s, datefrom='%s', dateto='%s', ordercost=%s, statusid=%s where id=%s";
	public final static String INSERT = "Insert into orders (userid, carid, datefrom, dateto, ordercost, statusid) values (%s, %s,'%s','%s',%s,%s)";

	public final static String TABLE_NAME = "orders";
	public final static String ID = "id";
	public final static String USER_ID = "userid";
	public final static String CAR_ID = "carid";
	public final static String DATE_FROM = "datefrom";
	public final static String DATE_TO = "dateto";
	public final static String ORDER_COST = "ordercost";
	public final static String STATUS_ID = "statusid";
	public final static String ADD_COST = "addcost";

	private Long carID;
	private Date dateFrom;
	private Date dateTo;
	private Long userId;
	private Double orderCost;
	private Double addCost;
	private OrderStatus status;
	private Long statusId;

	public Order(Long id, Long userId, Long carId, Date dateFrom, Date dateTo,
			Double orderCost, Long statusId) {
		this.id = id;
		this.userId = userId;
		this.carID = carId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.orderCost = orderCost;
		this.statusId=statusId;
	}

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

	public OrderStatus getState() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStateId(Long statusId) {
		this.statusId = statusId;
	}
	@Override
	public String toString() {
		return "Order [carID=" + carID + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + ", userId=" + userId + ", orderCost=" + orderCost
				+ ", addCost=" + addCost + ", status=" + status + ", statusId="
				+ statusId + "]";
	}

}
