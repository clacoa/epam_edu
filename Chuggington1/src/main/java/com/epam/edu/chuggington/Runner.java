package com.epam.edu.chuggington;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.epam.edu.chuggington.util.Property;

public class Runner {
	private static Logger LOG = Logger.getLogger(Runner.class);

	public static void main(String[] args) throws InterruptedException {
		
		Date dateStart = new Date(System.currentTimeMillis());

		LOG.info("Start Chuggington");

		Tunnel<Railway> tunnel = new Tunnel<Railway>();
		List<Train> trains = Property.getData(
				"src/main/resources/Chuggington.xml", tunnel);

		for (Train train : trains) {
			train.start();
		}
		for (Train train : trains) {
			train.join();
		}
		LOG.info("End Chuggington");
		Date dateEnd = new Date(System.currentTimeMillis());
		LOG.info(dateEnd.getTime()-dateStart.getTime());
	}
}
