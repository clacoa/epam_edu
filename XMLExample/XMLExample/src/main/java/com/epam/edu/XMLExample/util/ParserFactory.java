package com.epam.edu.XMLExample.util;

import javax.xml.bind.JAXBException;

public class ParserFactory {
	
	public static ChuggingtonParser getParser(ParserType parserType) throws JAXBException {
		ChuggingtonParser chuggingtonParser = null;
		switch (parserType){
		case SAX:
			chuggingtonParser= new ParserSAX();
			break;
		case StAX:
			chuggingtonParser=new ParserStAX();
			break;
		case DOM:
			chuggingtonParser = new ParserDOM();
			break;
		case JAXB:
			chuggingtonParser = new ParserJAXB();
			break;
		}
		return chuggingtonParser;
	}
}
