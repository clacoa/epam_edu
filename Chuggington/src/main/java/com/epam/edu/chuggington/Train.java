package com.epam.edu.chuggington;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

public class Train extends Thread {

	private String name;
	private int throwTunnelTime;
	private Direction direction;
	private Semaphore semaphore;
	private static Logger LOG = Logger.getLogger(Train.class);

	public String getTrainName() {
		return name;
	}

	public void setTrainName(String name) {
		this.name = name;
	}

	public int getThrowTunnelTime() {
		return throwTunnelTime;
	}

	public void setThrowTunnelTime(int throwTunnelTime) {
		this.throwTunnelTime = throwTunnelTime;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Train(String name, int throwTunnelTime, Direction direction,
			Semaphore semaphore) {
		super();
		this.name = name;
		this.throwTunnelTime = throwTunnelTime;
		this.direction = direction;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			for (int i = 0; i < 1000; i++) {
				Tunnel.holdRailway(this);
				//LOG.info(this.toString());
				this.sleep(this.getThrowTunnelTime() * 1L);
				Tunnel.clearRailway(this);
			}
			semaphore.release();

		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public String toString() {
		return name + " goes throw the tunnel on " + throwTunnelTime
				+ " seconds.";
	}

}
