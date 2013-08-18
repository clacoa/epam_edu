package com.epam.edu.workwithstrings;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.epam.edu.workwithstrings.textparts.Sentence;
import com.epam.edu.workwithstrings.textparts.SentencePart;
import com.epam.edu.workwithstrings.textparts.Text;
import com.epam.edu.workwithstrings.util.Properties;
import com.epam.edu.workwithstrings.util.TextReader;

public class MyTest {
	private static Logger LOG = Logger.getLogger(MyTest.class);
	private Text readText;

	@Before
	public void initialize() throws Exception {
		readText = new Text(TextReader.getText(
				Properties.getProperty("pathToFileForRead"), "UTF-8"));
	}

	@Test
	public void testTextAndSentence() {
		Assert.assertNotNull(readText);
		Sentence s = readText.get(0);
		Assert.assertNotNull("Fist sentence is null", s);
		print("====Sentence text is " + s.toString());
	}

	@Test
	public void testTextAndWord() {
		Assert.assertNotNull(readText);
		SentencePart w = readText.get(0).get(0);
		Assert.assertNotNull(w);
		print("====Word text is " + w.toString());
	}

	private void print(Object o) {
		System.out.println(o);
	}

}