package com.epam.edu.chuggington;

import java.util.List;
import java.util.concurrent.Semaphore;

import com.epam.edu.chuggington.util.Property;

public class Runner {

	public static void main(String[] args) {
		List<Train> trains = Property
				.getData("src/main/resources/Chuggington.xml");
		Semaphore semaphoreVee = new Semaphore(Integer.parseInt(Property
				.getProperty("numberoftunnels")));
		for (Train train : trains) {
			Tunnel tunnel = new Tunnel(semaphoreVee, train);
			tunnel.start();
		}
	}

}
