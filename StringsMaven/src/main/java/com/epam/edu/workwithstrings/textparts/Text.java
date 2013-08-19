package com.epam.edu.workwithstrings.textparts;

import java.text.BreakIterator;
import java.util.ArrayList;

import com.epam.edu.workwithstrings.util.TextParser;

public class Text extends TextPart<Sentence> {

	public Text(String text) {
		BreakIterator iterator = BreakIterator.getSentenceInstance();
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
				.next()) {
			this.add(new Sentence(text.substring(start, end)));
		}
	}
	public Text(String text, boolean flag){
		
		ArrayList<Sentence> sentences = TextParser.parsSentences(text);
		for (Sentence sentence : sentences){
			this.add(sentence);
		}
	}
}
