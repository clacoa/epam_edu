package com.epam.edu.chuggington;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.epam.edu.chuggington.util.Property;

public class Tunnel<T> extends LinkedList<Railway> {

	private static Logger LOG = Logger.getLogger(Tunnel.class);
	private int railway;
	private Semaphore semaphoreVee;

	public Semaphore getSemaphoreVee() {
		return semaphoreVee;
	}

	public void setSemaphoreVee(Semaphore semaphoreVee) {
		this.semaphoreVee = semaphoreVee;
	}

	public Tunnel() {
		railway = Integer.parseInt(Property.getProperty("numberoftunnels"));
		semaphoreVee = new Semaphore(railway);
		for (int i = 0; i < railway; i++) {
			this.add(new Railway(i + 1));
		}
	}

	public synchronized Railway getRailway() {
		Railway railway = this.get(0);
		this.remove(0);
		return railway;

	}

	public synchronized void setRailway(Railway railway) {
		this.add(railway);
	}

}
