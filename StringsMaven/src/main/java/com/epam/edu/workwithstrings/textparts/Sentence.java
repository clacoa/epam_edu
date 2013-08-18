package com.epam.edu.workwithstrings.textparts;

import java.text.BreakIterator;
import java.util.Locale;

public class Sentence extends TextPart<SentencePart> {

	public Sentence(String sentence) {
		BreakIterator iterator = BreakIterator.getWordInstance(new Locale("ru",
				"Ru"));
		iterator.setText(sentence);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
				.next()) {
			String sentencePart = sentence.substring(start, end);
			this.add(new Word(sentencePart));
		}
	}

}
