package com.epam.edu.oop.cookchief.dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;
import com.epam.edu.oop.cookchief.ingredients.MeasuredIngredient;
import com.epam.edu.oop.cookchief.ingredients.Vegetable;
import com.epam.edu.oop.cookchief.util.CookingWay;
import com.epam.edu.oop.cookchief.util.IngredientCreator;
import com.epam.edu.oop.cookchief.util.RegularFild;

public class SideDish {

	private List<Ingredient> sideDish = new ArrayList<Ingredient>();

	public SideDish(Map<String, Integer> ingredietMap) {

		IngredientCreator ingredientCreator = new IngredientCreator();

		for (String key : ingredietMap.keySet()) {
			sideDish.add(ingredientCreator.create(key, ingredietMap.get(key)));
		}
	}

	public void cooking() {
		for (CookingWay cooked : RegularFild.Cooked()) {
			for (Ingredient ingredient : sideDish) {
				if (cooked.ingredientName
						.equals(ingredient.getIngredientName())) {
					((Vegetable) ingredient).cook(cooked.cookingWay);
				}
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

	public List<Ingredient> getRange(int minCalories, int maxCalories) {

		List<Ingredient> range = new ArrayList<Ingredient>();
		for (Ingredient ingredient : sideDish) {
			if (ingredient instanceof Vegetable) {
				if ((((Vegetable) ingredient).getCalories() >= minCalories)
						& (((Vegetable) ingredient).getCalories() <= maxCalories)) {
					range.add(ingredient);
				}
			}
		}

		return range;
	} 

	public String getDescription() {
		String desString = "";
		for (Ingredient ingredient : sideDish) {
			desString += ingredient.getDescription() + "\n";
		}
		return desString;

	}

	@Override
	public String toString() {
		return getDescription();

	}
}
