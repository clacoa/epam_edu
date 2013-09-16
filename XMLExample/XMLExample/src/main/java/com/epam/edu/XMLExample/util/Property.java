package com.epam.edu.XMLExample.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Property {
	private static Logger LOG = Logger.getLogger(Property.class);
	private static ResourceBundle bundle;
	public static final String BUNDLE_NAME = "config";

	public static String getProperty(String propertyName) {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		try {
			return bundle.getString(propertyName);
		} catch (MissingResourceException e) {
			LOG.warn(e);
			return null;
		}

	}
}
