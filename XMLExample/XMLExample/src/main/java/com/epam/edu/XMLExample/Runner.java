package com.epam.edu.XMLExample;

import org.apache.log4j.Logger;

import com.epam.edu.XMLExample.util.ParserDOM;
import com.epam.edu.XMLExample.util.ParserSAX;
import com.epam.edu.xmlobject.Trains;

public class Runner {
	private static Logger LOG = Logger.getLogger(Runner.class);
	
    public static void main( String[] args ) {
    	LOG.info("Start parsing");
    	Trains saxTrains = ParserSAX.getData("src/main/resources/Chuggington.xml");
    	Trains domTrains = ParserDOM.getData("Chuggington.xml");
    	LOG.info("End parsing");
    }
}
