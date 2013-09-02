package com.epam.edu.chuggington;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.epam.edu.chuggington.util.Property;

public class Tunnel<E> extends ArrayList<Railway> {

	private static Logger LOG = Logger.getLogger(Tunnel.class);
	private int railway;
	private Semaphore semaphoreVee;

	public Tunnel() {
		railway = Integer.parseInt(Property.getProperty("numberoftunnels"));
		semaphoreVee = new Semaphore(railway);
		for (int i = 0; i < railway; i++) {
			this.add(new Railway());
		}
	}

	public  int getFreeIndex() {
		int index = -1;
		//while (index < 0) {
			for (Railway railway : this) {
				while (index < 0) {
				if (railway.isFree()) {
					index = this.indexOf(railway);
				}
			}
		}
		return index;
	}

	public synchronized void goToTunnel(Train train) {
		int railWayIndex = this.getFreeIndex();
		try {
			semaphoreVee.acquire();
			this.get(railWayIndex).holdRailway(train.getTrainName());
			LOG.info(train.toString() +" "+ railWayIndex+" railway.");
			Thread.sleep(train.getThrowTunnelTime() * 1L);
			this.get(railWayIndex).clearRailway(train.getTrainName());
			semaphoreVee.release();
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
