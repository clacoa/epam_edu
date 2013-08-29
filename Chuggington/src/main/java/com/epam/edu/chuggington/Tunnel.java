package com.epam.edu.chuggington;

import org.apache.log4j.Logger;

import com.epam.edu.chuggington.util.Property;

public class Tunnel {
	private static int railway = Integer.parseInt(Property
			.getProperty("numberoftunnels"));
	private static Logger LOG = Logger.getLogger(Tunnel.class);

	public static synchronized void holdRailway(Train train) {
		railway--;
		if (getRailWay()<0){
			LOG.warn(train.getTrainName() + " goes to throw tunnel.");
		}
		//LOG.info(train.getTrainName() + " throw tunnel.");
	}

	public static synchronized void clearRailway(Train train) {
		railway++;
		//LOG.info(train.getTrainName() + " free tunnel.");
	}

	public static int getRailWay() {
		return railway;
	}
}
