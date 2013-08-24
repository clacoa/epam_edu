package com.epam.edu.oop.cookchief.util;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;

public class SortedByName extends IngredientsSorter {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getIngredientName().compareTo(o2.getIngredientName());
	}

}
