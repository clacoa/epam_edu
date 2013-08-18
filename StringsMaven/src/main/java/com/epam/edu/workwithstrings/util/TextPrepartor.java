package com.epam.edu.workwithstrings.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.edu.workwithstrings.textparts.Punctuation;
import com.epam.edu.workwithstrings.textparts.Sentence;
import com.epam.edu.workwithstrings.textparts.SentencePart;
import com.epam.edu.workwithstrings.textparts.Text;

public class TextPrepartor {

	public static String calcSentCountByWord(Text text) {
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for (Sentence sentence : text) {
			List<String> sentWords = new ArrayList<String>();
			for (SentencePart word : sentence) {
				String key = word.toString().toLowerCase();
				if (!Punctuation.isPunctuation(key)) {
					if (!sentWords.contains(key)) {
						sentWords.add(key);
						wordMap.put(key,
								wordMap.get(key) == null ? 1
										: wordMap.get(key) + 1);
					}
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
		return "Word \"" + maxValueKey + "\" has " + maxValue + " matches.";
	}

	public static String sortWordsByAlphabet(Text text) {
		Map<String, String> wordMap = new HashMap<String, String>();
		for (Sentence sentence : text) {
			for (SentencePart word : sentence) {
				String key = word.toString().toLowerCase().substring(0, 1);
				if (!Punctuation.isPunctuation(key)) {
					wordMap.put(key,
							(wordMap.get(key) != null ? wordMap.get(key) + " "
									: "") + word.toString());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c = 'à'; c <= 'ÿ'; c++) {
			if (wordMap.get(String.valueOf(c)) != null) {
				sb.append(wordMap.get(String.valueOf(c)) + "\n");
			}
		}
		return sb.toString();
	}

}
