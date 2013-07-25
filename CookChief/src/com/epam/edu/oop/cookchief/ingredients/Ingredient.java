package com.epam.edu.oop.cookchief.ingredients;

public class Ingredient {

	private String ingredientName;

	public Ingredient(String ingredientName) {
		this.setIngredientName(ingredientName);
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getDescription(){
		return getIngredientName();
	}
}
