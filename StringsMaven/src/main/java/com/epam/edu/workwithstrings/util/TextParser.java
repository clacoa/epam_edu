package com.epam.edu.workwithstrings.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.edu.workwithstrings.textparts.Sentence;

public class TextParser {

	private static Pattern pattern;
	private static Matcher matcher;

	public static ArrayList<Sentence> parsSentences(String text) {

		String sentence = "";
		int sentStart = 0;
		int sentEnd = 1;

		pattern = Pattern.compile("[.!?] [À-ß]|\\n");
		//pattern = Pattern.compile("\\n");
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		matcher = pattern.matcher(text);
		boolean found = false;
		while (matcher.find()) {
			sentEnd = matcher.start()+1;
			sentence = (text.substring(sentStart, sentEnd));
			if (!sentence.equals("")){
			sentences.add(new Sentence(sentence));
			}
			matcher.group();
			matcher.start();
			matcher.end();
			sentStart = matcher.end()-2;
			found = true;
		}
		if (!found) {
			System.out.println("No match found.");
		}
		return sentences;

	}
}
