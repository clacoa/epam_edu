package com.epam.edu.XMLExample.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;



import org.apache.log4j.Logger;

import com.epam.edu.xmlobject.Trains;

public class ParserSTAX {
	private static Logger LOG = Logger.getLogger(ParserSTAX.class);
	
	private final static Trains trains = new Trains();
	

	private XMLInputFactory factory = XMLInputFactory.newInstance();
	private StringBuilder chars;
	private boolean isTrainTag;
}
