package com.epam.edu.chuggington;

import org.apache.log4j.Logger;

public class Railway {
	private int railway = 1;
	private boolean free = true;

	private static Logger LOG = Logger.getLogger(Railway.class);

	public Railway() {
	}

	public void holdRailway(String trainName) {
		railway--;
		setFree(false);
		if (railway < 0) {
			LOG.warn(trainName + " goes to throw tunnel.");
		}
		 //LOG.info(trainName + " throw tunnel.");
	}

	public void clearRailway(String trainName) {
		railway++;
		if (railway > 1) {
			LOG.warn(trainName + " free one more tunnel.");
		}
		setFree(true);
		//LOG.info(trainName + " free tunnel.");
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
}
