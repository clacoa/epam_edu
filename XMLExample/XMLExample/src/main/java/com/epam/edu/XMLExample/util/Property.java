package com.epam.edu.XMLExample.util;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Property extends ParserSAX {
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
}
