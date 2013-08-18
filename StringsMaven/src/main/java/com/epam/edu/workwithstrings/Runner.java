package com.epam.edu.workwithstrings;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.epam.edu.workwithstrings.textparts.Sentence;
import com.epam.edu.workwithstrings.textparts.Text;
import com.epam.edu.workwithstrings.util.Properties;
import com.epam.edu.workwithstrings.util.TextPrepartor;
import com.epam.edu.workwithstrings.util.TextReader;
import com.epam.edu.workwithstrings.util.TextWriter;

public class Runner {

	private static Logger LOG = Logger.getLogger(Runner.class);

	public static void main(String args[]) throws ClassNotFoundException,
			IOException {

		Text readText = new Text(TextReader.getText(
				Properties.getProperty("pathToFileForRead"), "UTF-8"));
		TextWriter.writeText(readText.toString(),
				Properties.getProperty("pathToFileForWrite"));
		System.out.println(readText.get(4));
		readText.add(new Sentence("Добавленное предложение."));
		System.out.println(readText.get(4).get(0));
		System.out.println(TextPrepartor.calcSentCountByWord(readText));
		System.out.println(TextPrepartor.sortWordsByAlphabet(readText));
		LOG.info("closed");
		return;
	}
}
