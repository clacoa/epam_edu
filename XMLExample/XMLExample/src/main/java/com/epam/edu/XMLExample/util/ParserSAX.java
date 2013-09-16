package com.epam.edu.XMLExample.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.edu.xmlobject.Train;
import com.epam.edu.xmlobject.Trains;

public class ParserSAX implements ChuggingtonParser{

	private static Logger LOG = Logger.getLogger(ParserSAX.class);
	private static XMLReader xmlReader;
	private static TrainHandler saxHandler;
	private final static Trains trains= new Trains();
	private SAXParserFactory spfactory;
	private static SAXParser saxParser;

	public Trains getData(String dataFileName) {

		xmlReader = null;
		saxHandler = new TrainHandler(trains);

		try {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			SAXParser saxParser = spfactory.newSAXParser();
			xmlReader = saxParser.getXMLReader();
			saxParser.parse(ClassLoader
						.getSystemResourceAsStream(dataFileName), saxHandler);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return trains;
	}
}