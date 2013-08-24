package com.epam.edu.chuggington;

public class Train {

	private String name;
	private int throwTunnelTime;
	private Direction direction;

	

	public Train(String name, int throwTunnelTime, Direction direction) {
		this.name = name;
		this.throwTunnelTime = throwTunnelTime;
		this.direction = direction;
	}

	public String getTrainName() {
		return name;
	}

	public void setTrainName(String trainName) {
		this.name = trainName;
	}

	public int getThrowTunnelTime() {
		return throwTunnelTime;
	}

	public void setThrowTunnelTime(int trainTunnelTime) {
		this.throwTunnelTime = trainTunnelTime;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return name + " goes throw the tunnel on "
				+ throwTunnelTime + " seconds.";

	}

}
