package com.epam.edu.chuggington;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.epam.edu.chuggington.util.Property;

public class Runner {
	private static Logger LOG = Logger.getLogger(Runner.class);

	public static void main(String[] args) {

		LOG.info("Start Chuggington");

		Tunnel<Railway> tunnel = new Tunnel<Railway>();
		List<Train> trains = Property.getData(
				"src/main/resources/Chuggington.xml", tunnel);

		for (Train train : trains) {
			train.start();
		}
		LOG.info("End Chuggington");
	}
}
