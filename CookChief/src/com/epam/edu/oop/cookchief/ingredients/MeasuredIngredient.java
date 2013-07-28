package com.epam.edu.oop.cookchief.ingredients;

import com.epam.edu.oop.cookchief.util.RegularFild;

public abstract class MeasuredIngredient extends Ingredient implements
		Processed {

	private int calories;

	public MeasuredIngredient(String ingredientName, int calories) {
		super(ingredientName);
		this.setCalories(calories);
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	@Override
	public String getDescription() {
		return this.getIngredientName() + " " + this.getCalories() + " "
				+ RegularFild.MEASUR;
	}
	
	@Override
	public String toString(){
		return getDescription();
	}
}
