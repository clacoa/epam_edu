package com.epam.edu.chuggington;

import org.apache.log4j.Logger;

public class Railway {

	private int railway = 1;
	private int railwayNum;

	private static Logger LOG = Logger.getLogger(Railway.class);

	public Railway(int railwayNum) {
		this.railwayNum = railwayNum;
	}

	public void holdRailway(String trainName) {
		railway--;
		if (railway < 0) {
			LOG.warn(trainName + " goes to throw tunnel¹" + railwayNum);
		}
		LOG.info(trainName + " throw tunnel ¹" + railwayNum);
	}

	public void clearRailway(String trainName) {

		railway++;
		if (railway > 1) {
			LOG.warn(trainName + " free one more tunnel ¹" + railwayNum);
		}
		LOG.info(trainName + " free tunnel ¹" + railwayNum);
	}
}
