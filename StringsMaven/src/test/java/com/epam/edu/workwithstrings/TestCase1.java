package com.epam.edu.workwithstrings;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.edu.workwithstrings.textparts.Text;
import com.epam.edu.workwithstrings.util.Properties;
import com.epam.edu.workwithstrings.util.TextReader;

public class TestCase1 {
	private static Logger LOG = Logger.getLogger(TestCase1.class);
	private Text readText;

	@Before
	public void initialize() throws Exception {
		readText = new Text(TextReader.getText(
				Properties.getProperty("pathToFileForRead"), "UTF-8"));
	}

	@Test
	public void testText() {
		Assert.assertNotNull(readText);
		Assert.assertTrue("Sentences not empty", readText.size() > 0);
		print("====Size of Sentences is " + readText.size());
	}

	@Test
	public void bazinga() {
		Assert.assertNotNull(readText.toString());
	}

	private void print(Object o) {
		System.out.println(o);
	}

}