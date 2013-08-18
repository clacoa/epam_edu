package com.epam.edu.workwithstrings.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextReader {

	public static String getText(String pathToFile, String encoding)
			throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(pathToFile);
		InputStreamReader inputStreamReader = new InputStreamReader(
				fileInputStream, encoding);
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		String lines = "";
		String line;
		while ((line = bufferReader.readLine()) != null) {
			lines += line + "\n";
		}
		bufferReader.close();
		return lines;
	}

}
