package com.epam.edu.oop.cookchief.util;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;
import com.epam.edu.oop.cookchief.ingredients.Vegetable;

public class IngredientCreator {

	public Ingredient create(String ingredientName, Integer calories) {
		if (calories != null) {
			return (new Vegetable(ingredientName, calories));
		} else {
			return (new Ingredient(ingredientName));
		}
	}

}
