package com.epam.edu.XMLExample.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.epam.edu.xmlobject.Trains;

public class ParserJAXB implements ChuggingtonParser {

	private static Logger LOG = Logger.getLogger(ParserJAXB.class);
	private Trains trains;
	private Unmarshaller unmarshaller;
	private Marshaller marshaller;
	private JAXBContext jc;
	
	public ParserJAXB() throws JAXBException{
		jc = JAXBContext.newInstance(Trains.class);
	}

	public Trains getData(String dataFileName) throws JAXBException,
			SAXException {
		unmarshaller = jc.createUnmarshaller();
		trains = (Trains) unmarshaller.unmarshal(ClassLoader
				.getSystemResourceAsStream(dataFileName));
		return trains;
	}

	public void writeXML(String targetFileName) throws JAXBException {
		marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		try {
			OutputStream os = new FileOutputStream(targetFileName);
			marshaller.marshal(trains, os);
		} catch (FileNotFoundException e) {
			LOG.warn(e);
		}
		// marshaller.marshal(trains, System.out);
	}

}
