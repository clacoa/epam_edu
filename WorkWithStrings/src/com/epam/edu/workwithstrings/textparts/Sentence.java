package com.epam.edu.workwithstrings.textparts;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class Sentence {
	private String sentence;
	private List<Word> words;

	public Sentence(String sentence) {
		this.sentence = sentence;
	}

	public List<Word> getWords() {
		if (words == null) {
			words = new ArrayList<Word>();
			BreakIterator iterator = BreakIterator
					.getWordInstance();
			iterator.setText(sentence);
			int start = iterator.first();
			for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
					.next()) {
				String word = sentence.substring(start, end);
				if (!Punctuation.isPunctuation(word)){
					words.add(new Word(word));
					System.out.println(word);
				}
			}
		}
		return words;
	}
}
