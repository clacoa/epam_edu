package com.epam.edu.workwithstrings.textparts;

import java.util.Arrays;
import java.util.List;

public class Punctuation {

	private static final String[] punctuationArray = 
		{".",",",":",";","!","?","-","«","»","...","(",")","'","","{","}","—"," ","\n"};
	
	public static List<String> getPunctuationList(){
		List<String> list = Arrays.asList(punctuationArray);
		return list;
	}
	public static boolean  isPunctuation(String s){
		return Arrays.asList(punctuationArray).contains(s);
	}
}
