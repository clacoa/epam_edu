package com.epam.edu.oop.cookchief.ingredients;

import com.epam.edu.oop.cookchief.util.CookEnum;

public class Ingredient {

	private String ingredientName;
	private CookEnum cookingWay;

	public Ingredient(String ingredientName) {
		this.ingredientName = ingredientName;
		this.cookingWay = CookEnum.FRESH;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getDescription() {
		return getIngredientName();
	}

	public CookEnum getCookingWay() {
		return cookingWay;
	}

	public void setCookingWay(CookEnum cookingWay) {
		this.cookingWay = cookingWay;
	}

	@Override
	public String toString() {
		return getDescription();
	}

}