package com.epam.edu.workwithstrings.textparts;

import java.util.ArrayList;
import java.util.List;

public class Word {
	private String word;
	private char[] chars;

	public Word(String word) {
		this.word = word;
	}

	public List<Symbol> getSymbols() {
		List<Symbol> symbols = new ArrayList<Symbol>();
		if (chars == null) {
			chars = new char[word.length()];
			word.getChars(0, word.length(), chars, 0);
			for (char c : chars) {
				symbols.add(new Symbol(c));
				System.out.println(c);
			}
		}
		return symbols;
	}
	@Override
	public String toString(){
		return word;
	}
}
