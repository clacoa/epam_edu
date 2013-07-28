package com.epam.edu.oop.cookchief.ingredients;

import com.epam.edu.oop.cookchief.util.CookEnum;

public class Ingredient implements Cloneable {

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
	
	public Ingredient clone(){
		Ingredient ingredient=null;
		try {
			ingredient = (Ingredient)(super.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return ingredient;
		
	}

	@Override
	public String toString() {
		return getDescription();
	}

}