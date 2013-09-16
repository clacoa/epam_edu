package com.epam.edu.XMLExample.util;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.epam.edu.xmlobject.Trains;

public interface ChuggingtonParser {
	public Trains getData(String fileName) throws JAXBException, SAXException;

}
