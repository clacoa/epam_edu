package com.epam.edu.workwithstrings.textparts;

import java.util.ArrayList;

public abstract class TextPart<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T part : this) {
			sb.append(part.toString());
		}
		return sb.toString();
	}

}
