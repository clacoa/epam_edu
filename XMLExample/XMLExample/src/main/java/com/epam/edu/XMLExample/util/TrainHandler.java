package com.epam.edu.XMLExample.util;

import java.math.BigInteger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.edu.xmlobject.Train;
import com.epam.edu.xmlobject.Trains;

public class TrainHandler  extends DefaultHandler {
	boolean isTrainID=false;
	boolean isName = false;
	boolean isDirection = false;
	boolean isTime = false;
	String trainID;
	String name;
	String direction;
	int time;
	Trains trains;
	
	public TrainHandler(Trains trains){
		this.trains=trains;
	}
	
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes)
			throws SAXException {
		if (qName.equalsIgnoreCase("ch:trainID")) {
			isTrainID = true;
		}
		if (qName.equalsIgnoreCase("ch:name")) {
			isName = true;
		}

		if (qName.equalsIgnoreCase("ch:throwTunnelTime")) {
			isTime = true;
		}

		if (qName.equalsIgnoreCase("ch:direction")) {
			isDirection = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if (qName.equalsIgnoreCase("ch:train")) {
			Train train = new Train();
			train.setTrainID(trainID);
			train.setName(name);
			train.setThrowTunnelTime(BigInteger.valueOf(time));
			train.setDirection(direction);
			trains.addTrain(train);
		}

	}
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (isTrainID) {
			trainID = new String(ch, start, length);
			isTrainID = false;
		}
		if (isName) {
			name = new String(ch, start, length);
			isName = false;
		}

		if (isDirection) {
			direction = new String(ch, start, length);
			isDirection = false;
		}

		if (isTime) {
			time = Integer.parseInt(new String(ch, start, length));
			isTime = false;
		}
	}
	
}
