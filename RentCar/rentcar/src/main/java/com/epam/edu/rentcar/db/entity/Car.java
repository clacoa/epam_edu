package com.epam.edu.rentcar.db.entity;

public class Car extends AbstractEntity {
 
	public final static String UPDATE = "Update cars Set carcost=%s, mark='%s', model='%s', description='%s', statusid=%s where id=%s";
	public final static String INSERT = "Insert into cars (carcost, mark, model, description, statusid) values (%s,'%s','%s','%s',%s)";
	
	public final static String TABLE_NAME="cars";
	public final static String ID = "id";
	public final static String MARK = "mark";
	public final static String MODEL = "model";
	public final static String DESCRIPTION = "description";
	public final static String CAR_COST = "carcost";
	public final static String STATUS_ID = "statusid";
	
	private Double cost;
	private String mark;
	private String model;
	private String description;
	private Long statusId;
	private CarStatus status;
	
	
	public Car(Long id, String mark, String model, Double cost, Long statusId){
		this.id = id;
		this.mark = mark;
		this.model= model;
		this.cost = cost;
		this.statusId = statusId;
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
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public CarStatus getStatus() {
		return status;
	}
	public void setStatus(CarStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Car [cost=" + cost + ", mark=" + mark + ", model=" + model
				+ ", description=" + description + ", idStatus=" + statusId
				+ ", status=" + status + "]";
	}

}
