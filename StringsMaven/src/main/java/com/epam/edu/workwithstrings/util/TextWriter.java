package com.epam.edu.workwithstrings.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class TextWriter {
	public static void writeText(String text, String pathToFile)
			throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
		fileOutputStream.write(text.getBytes());
		fileOutputStream.close();
	}
}
