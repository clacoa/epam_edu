package com.epam.edu.rentcar.model;

import org.apache.commons.lang3.StringUtils;

public class CarFilter {
	
	private String mark;
	private String model;
	private Double minCost;
	private Double maxCost;
	private String description;
	
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
	public Double getMinCost() {
		return minCost;
	}
	public void setMinCost(Double minCost) {
		this.minCost = minCost;
	}
	public Double getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(Double maxCost) {
		this.maxCost = maxCost;
	}
	public String getDescription() {
		return (StringUtils.isEmpty(description) ? "" : description);
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getModelName(){
		if (!StringUtils.isEmpty(mark) &&
				!StringUtils.isEmpty(model)){
			return mark+"~"+model;
		}
		return "";
	}
	
	public String getCost(){
		if (minCost!=null && maxCost!=null){
			return minCost.intValue()+"~"+maxCost.intValue();
		}
		return "";
	}

}
