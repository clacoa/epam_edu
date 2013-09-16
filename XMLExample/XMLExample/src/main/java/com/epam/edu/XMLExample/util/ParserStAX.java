package com.epam.edu.XMLExample.util;

import java.math.BigInteger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.edu.xmlobject.Train;
import com.epam.edu.xmlobject.Trains;

public class ParserStAX implements ChuggingtonParser {

	private static Logger LOG = Logger.getLogger(ParserStAX.class);
	private final static Trains trains = new Trains();
	private static XMLInputFactory factory = XMLInputFactory.newInstance();

	public Trains getData(String dataFileName) {
		Train train = null;
		Trains trains = null;
		String tagContent = null;

		try {
			XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader
					.getSystemResourceAsStream(dataFileName));

			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:

					if ("train".equals(reader.getLocalName())) {						
						train = new Train();
						train.setId(reader.getAttributeValue(null, "id"));
					}
					if ("trains".equals(reader.getLocalName())) {
						trains = new Trains();
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "train":
						trains.addTrain(train);
						break;
					case "name":
						train.setName(tagContent);
						break;
					case "throwTunnelTime":
						train.setThrowTunnelTime(BigInteger.valueOf(Long
								.valueOf(tagContent)));
						break;
					case "direction":
						train.setDirection(tagContent);
						break;
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			LOG.error(e.getMessage());
		}

		return trains;
	}

}
