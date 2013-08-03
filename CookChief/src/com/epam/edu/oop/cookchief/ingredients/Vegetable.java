package com.epam.edu.oop.cookchief.ingredients;

import com.epam.edu.oop.cookchief.util.CookEnum;

public class Vegetable extends MeasuredIngredient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -556617257950963739L;

	public Vegetable(String ingredientName, int calories) {
		super(ingredientName, calories);
	}

	@Override
	public void cook(CookEnum cookType) {
		double factor = 1;
		String name = cookType.getDiscription();
		this.setIngredientName(this.getIngredientName() + " " + "(" + name
				+ ")");

		if (this.getCalories() != 0) {
			if (cookType == CookEnum.BOIL) {
				factor = 1.02;
			} else if (cookType == CookEnum.FRY) {
				factor = 2;
			} else if (cookType == CookEnum.ROAST) {
				factor = 1.3;
			} else if (cookType == CookEnum.MARINATE) {
				factor = 0.9;
			}
			this.setCalories((int) (this.getCalories() * factor));
		}
	}
}
