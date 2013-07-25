package com.epam.edu.oop.cookchief.dich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;
import com.epam.edu.oop.cookchief.ingredients.MeasuredIngredient;
import com.epam.edu.oop.cookchief.ingredients.Vegetable;

public class SideDish {

	private List<Ingredient> sideDish = new ArrayList<Ingredient>();

	public SideDish(Map<String, Integer> ingredietMap) {
		for (String key : ingredietMap.keySet()) {
			if (ingredietMap.get(key) != null) {
				sideDish.add(new Vegetable(key, ingredietMap.get(key)));
			} else {
				sideDish.add(new Ingredient(key));
			}
		}
	}

	public void sortByName() {
		Collections.sort(sideDish, new Comparator<Ingredient>() {

			@Override
			public int compare(Ingredient o1, Ingredient o2) {
				return o1.getIngredientName().compareTo(o2.getIngredientName());
			}
		});
	}

	public void sortByCalories() {
		Collections.sort(sideDish, new Comparator<Ingredient>() {

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
		});
	}

	public String getDesription() {
		String desString = "";
		for (Ingredient ingredient : sideDish) {
			desString += ingredient.getDescription() + "\n";
		}
		return desString;

	}
}
