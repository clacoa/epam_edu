package com.epam.edu.XMLExample.util;

import java.io.IOException;
import java.math.BigInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.edu.xmlobject.Train;
import com.epam.edu.xmlobject.Trains;

public class ParserDOM {

	private static Logger LOG = Logger.getLogger(ParserDOM.class);
	private static  Trains trains = new Trains();

	public static Trains getData(String dataFileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				Document document = builder.parse(ClassLoader
						.getSystemResourceAsStream(dataFileName));
				NodeList nodeList = document.getDocumentElement()
						.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++) {

					// We have encountered an <employee> tag.

					Node node = nodeList.item(i);

					if (node instanceof Element) {
						Train train = new Train();
						NodeList childNodes = node.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node cNode = childNodes.item(j);
							if (cNode instanceof Element) {
								String content = cNode.getLastChild().
								getTextContent().trim();
								switch (cNode.getNodeName()) {
								case "ch:trainID":
									train.setTrainID(content);
									break;
								case "ch:name":
									train.setName(content);
									break;
								case "ch:throwTunnelTime":
									train.setThrowTunnelTime(BigInteger.valueOf(Long.parseLong(content)));
									break;
								case "ch:direction":
									train.setDirection(content);
									break;
								}
							}
						}
						trains.addTrain(train);
					}
				}

			} catch (SAXException e) {
				LOG.error(e.getMessage());
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		} catch (ParserConfigurationException e) {
			LOG.error(e.getMessage());
		}

		return trains;
	}
}
