package com.epam.edu.workwithstrings.textparts;

public class Word extends SentencePart {

	public Word(String word) {

		char[] chars = new char[word.length()];
		word.getChars(0, word.length(), chars, 0);
		for (char c : chars) {
			this.add(new Symbol(c));
			System.out.println(c);
		}
	}
}
