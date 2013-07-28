package com.epam.edu.oop.cookchief.util;

public class CookingWay {

	public String ingredientName;
	public CookEnum cookingWay;
	
	public CookingWay(String ingredientName, CookEnum cookingWay){
		this.ingredientName=ingredientName;
		this.cookingWay=cookingWay;
	}
}
