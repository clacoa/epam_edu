package com.epam.edu.workwithstrings.textparts;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text {

	private String text;
	private List<Sentence> sentences;

	public Text(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}

	public List<Sentence> getSentences() {
		if (sentences == null) {
			sentences = new ArrayList<Sentence>();
			BreakIterator iterator = BreakIterator.getSentenceInstance();
			iterator.setText(text);
			int start = iterator.first();
			for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
					.next()) {
				sentences.add(new Sentence(text.substring(start, end)));
			}
		}
		return sentences;
	}

	public void getSentCountByWord() {
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for (Sentence sentence : getSentences()) {
			List<String> sentWords = new ArrayList<String>();
			for (Word word : sentence.getWords()) {
				String key = word.toString().toLowerCase();
				if (!sentWords.contains(key)) {
					sentWords.add(key);
					wordMap.put(key,
							wordMap.get(key) == null ? 1 : wordMap.get(key) + 1);
				}
			}
		}
		int maxValue = 0;
		String maxValueKey = "";
		for (String key : wordMap.keySet()) {
			if (wordMap.get(key) > maxValue) {
				maxValue = wordMap.get(key);
				maxValueKey = key;
			}
		}
		return;
	}

	public void sortWordsByAlphabet() {
		Map<String, String> wordMap = new HashMap<String, String>();
		for (Sentence sentence : getSentences()) {
			for (Word word : sentence.getWords()) {
				String key = word.toString().toLowerCase().substring(0, 1);
				wordMap.put(key, (wordMap.get(key) != null ? wordMap.get(key)+ " " 
						: "") + word.toString());
			}
		}
		for (char c = 'а'; c <= 'я'; c++) {
			if (wordMap.get(String.valueOf(c))!=null) {
				System.out.println(wordMap.get(String.valueOf(c)));
			}
		}
	}
}
