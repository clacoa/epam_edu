package com.epam.edu.workwithstrings;

import java.io.IOException;

import com.epam.edu.workwithstrings.textparts.Text;
import com.epam.edu.workwithstrings.util.Properties;
import com.epam.edu.workwithstrings.util.TextReader;
import com.epam.edu.workwithstrings.util.TextWriter;

public class Runner {

	public static void main(String args[]) throws ClassNotFoundException,
			IOException {

		Text readText = new Text(TextReader.getText(
				Properties.getProperty("pathToFileForRead"), "UTF-8"));
		TextWriter.writeText(readText.toString(),
				Properties.getProperty("pathToFileForWrite"));
		readText.getSentences().get(4).getWords();
		readText.getSentences().get(4).getWords().get(0).getSymbols();
		readText.getSentCountByWord();
		readText.sortWordsByAlphabet();
		return;
	}

}
