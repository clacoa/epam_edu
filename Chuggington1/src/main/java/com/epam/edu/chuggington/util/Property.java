package com.epam.edu.chuggington.util;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.edu.chuggington.Direction;
import com.epam.edu.chuggington.Train;
import com.epam.edu.chuggington.Tunnel;

public class Property {
	private static Logger LOG = Logger.getLogger(Property.class);
	private static ResourceBundle bundle;
	public static final String BUNDLE_NAME = "config";
	public static final String DATA = "data";

	public static String getProperty(String propertyName) {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		try {
			return bundle.getString(propertyName);
		} catch (MissingResourceException e) {
			return null;
		}

	}

	public static List<Train> getData(String dataFileName, final Tunnel tunnel) {

		final List<Train> trains = new ArrayList<Train>();

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				boolean isName = false;
				boolean isDirection = false;
				boolean isTime = false;
				String name = "";
				int direction = 0;
				int time = 0;

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equalsIgnoreCase("p:name")) {
						isName = true;
					}

					if (qName.equalsIgnoreCase("p:throwTunnelTime")) {
						isTime = true;
					}

					if (qName.equalsIgnoreCase("p:direction")) {
						isDirection = true;
					}
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equalsIgnoreCase("p:train")) {
						Train train = new Train(name, time,
								direction == 0 ? Direction.TO_MINES
										: Direction.TO_DEPO, tunnel);
						trains.add(train);
					}

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (isName) {
						name = new String(ch, start, length);
						isName = false;
					}

					if (isDirection) {
						direction = Integer.parseInt(new String(ch, start,
								length));
						isDirection = false;
					}

					if (isTime) {
						time = Integer.parseInt(new String(ch, start, length));
						isTime = false;
					}
				}

			};
			saxParser.parse(dataFileName, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trains;
	}
}
