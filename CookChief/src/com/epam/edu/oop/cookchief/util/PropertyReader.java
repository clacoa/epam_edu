package com.epam.edu.oop.cookchief.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

	private Properties properties = new Properties();

	public void loadProperty(String pathToFile) {

		try {
			properties.load(new InputStreamReader(PropertyReader.class
					.getResourceAsStream(pathToFile), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getProperties() {
		Map<String, Integer> ingredientMap = new HashMap<String, Integer>();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			ingredientMap.put(
					value.split(";")[0],
					(value.split(";").length > 1) ? Integer.valueOf(value
							.split(";")[1]) : null);
		}
		return ingredientMap;
	}
}
