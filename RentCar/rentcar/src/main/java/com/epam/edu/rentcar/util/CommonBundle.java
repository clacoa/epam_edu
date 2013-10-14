package com.epam.edu.rentcar.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class CommonBundle {
	private static Logger LOG = Logger.getLogger(CommonBundle.class);
	private static ResourceBundle bundle;
	public static final String BUNDLE_NAME = "i18n";

	public static String getProperty(String propertyName, Locale currentLocale) {
		if (currentLocale==null){
			currentLocale=new Locale("ru");
		}
		bundle = ResourceBundle.getBundle(BUNDLE_NAME,currentLocale);
		try {
			return bundle.getString(propertyName);
		} catch (MissingResourceException e) {
			LOG.warn(e);
			return null;
		}

	}
}
