package com.epam.edu.chuggington;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

public class Tunnel extends Thread {

	private static Logger LOG = Logger.getLogger(Tunnel.class);

	private Semaphore semaphore;
	private Train train;

	public Tunnel(Semaphore semaphore, Train train) {
		super();
		this.semaphore = semaphore;
		this.train = train;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			LOG.info(train.getTrainName() + " "
					+ train.getDirection()+" started");	
			LOG.info(train.toString());
			Tunnel.sleep(train.getThrowTunnelTime() * 1000L);
			LOG.info(train.getTrainName() + " "
					+ train.getDirection() + " arrived");
			semaphore.release();			
			
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}

	}

}