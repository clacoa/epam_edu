package com.epam.edu.workwithstrings.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Properties {
	private static ResourceBundle bundle;
	public static final String BUNDLE_NAME = "resources.configBundle";

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
