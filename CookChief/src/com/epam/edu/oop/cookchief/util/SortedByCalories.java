package com.epam.edu.oop.cookchief.util;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;
import com.epam.edu.oop.cookchief.ingredients.MeasuredIngredient;

public class SortedByCalories extends IngredientsSorter {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (!(o1 instanceof MeasuredIngredient)) {
			return -1;
		}
		if (!(o2 instanceof MeasuredIngredient)) {
			return 1;
		}

		if (((MeasuredIngredient) o1).getCalories() > ((MeasuredIngredient) o2)
				.getCalories()) {
			return 1;
		} else if (((MeasuredIngredient) o1).getCalories() < ((MeasuredIngredient) o2)
				.getCalories()) {
			return -1;
		} else {
			return 0;
		}
	}

}
