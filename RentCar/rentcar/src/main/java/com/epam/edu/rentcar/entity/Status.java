package com.epam.edu.rentcar.entity;

import org.apache.log4j.Logger;

public class Status extends AbstractEntity{
	
	private static Logger LOG = Logger.getLogger(Status.class);
	
	private String status;
	
	public Status(){
		
	}
	public Status(Long id) {
		super(id);
	}
	public Status(Long id, String status) {
		super(id);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "status = " + status ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Status other = (Status) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}
