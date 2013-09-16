package com.epam.edu.XMLExample;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.epam.edu.XMLExample.util.ChuggingtonParser;
import com.epam.edu.XMLExample.util.ParserFactory;
import com.epam.edu.XMLExample.util.ParserJAXB;
import com.epam.edu.XMLExample.util.ParserType;
import com.epam.edu.XMLExample.util.Property;
import com.epam.edu.xmlobject.Trains;

public class Runner {
	private static Logger LOG = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		LOG.info("Start parsing");
		ChuggingtonParser parser;
		Trains saxTrains = null;
		Trains domTrains = null;
		Trains staxTrains = null;
		Trains jaxbTrains = null;
		String sourceFile = Property.getProperty("sourceFile");
		String targetFile = Property.getProperty("targetFile");
		
		try {
			parser = ParserFactory.getParser(ParserType.SAX);
			saxTrains = parser.getData(sourceFile);
			parser = ParserFactory.getParser(ParserType.DOM);
			domTrains = parser.getData(sourceFile);
			parser = ParserFactory.getParser(ParserType.StAX);
			staxTrains = parser.getData(sourceFile);
			parser = ParserFactory.getParser(ParserType.JAXB);
			jaxbTrains = parser.getData(sourceFile);
			((ParserJAXB)parser).writeXML(targetFile);
		} catch (JAXBException e) {
			LOG.warn(e.getMessage());
		} catch (SAXException e) {
			LOG.warn(e.getMessage());
		}
		
		LOG.info("SAXParser: "+saxTrains.toString());
		LOG.info("StAXParser: "+staxTrains.toString());
		LOG.info("DOMParser: "+domTrains.toString());
		LOG.info("JAXBParser: "+jaxbTrains.toString());
		LOG.info("End parsing");
	}
}
