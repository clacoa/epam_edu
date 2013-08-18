package com.epam.edu.workwithstrings.textparts;

public class Symbol {
	private char symbol;

	public Symbol(char c) {
		symbol = c;
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}

}
