package com.epam.edu.chuggington;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

public class Train extends Thread {

	private String name;
	private int throwTunnelTime;
	private Direction direction;
	private Tunnel<Railway> tunnel;
	private Railway railway;
	private static Logger LOG = Logger.getLogger(Train.class);
	
	
	public Train(String name, int throwTunnelTime, Direction direction,
			Tunnel<Railway> tunnel) {
		super();
		this.name = name;
		this.throwTunnelTime = throwTunnelTime;
		this.direction = direction;
		this.tunnel = tunnel;
	}

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

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			tunnel.goToTunnel(this);
		}
	}

	@Override
	public String toString() {
		return name + " goes throw the tunnel on " + throwTunnelTime
				+ " seconds.";
	}

}
