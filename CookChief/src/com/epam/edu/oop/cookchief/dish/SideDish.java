package com.epam.edu.oop.cookchief.dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.epam.edu.oop.cookchief.ingredients.Ingredient;
import com.epam.edu.oop.cookchief.ingredients.MeasuredIngredient;
import com.epam.edu.oop.cookchief.ingredients.Vegetable;
import com.epam.edu.oop.cookchief.util.CookingWay;
import com.epam.edu.oop.cookchief.util.IngredientCreator;
import com.epam.edu.oop.cookchief.util.RegularFild;
import com.epam.edu.oop.cookchief.util.SortedByCalories;
import com.epam.edu.oop.cookchief.util.SortedByName;

public class SideDish implements Cloneable, Comparable<SideDish>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6095545432708811919L;
	/**
	 * 
	 */
	private List<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private String sideDishName;

	public SideDish(String sideDishName, Map<String, Integer> ingredietMap) {

		this.sideDishName = sideDishName;
		IngredientCreator ingredientCreator = new IngredientCreator();

		for (String key : ingredietMap.keySet()) {
			ingredientList.add(ingredientCreator.create(key,
					ingredietMap.get(key)));
		}
	}

	public void cooking() {
		for (CookingWay cooked : RegularFild.Cooked()) {
			for (Ingredient ingredient : ingredientList) {
				if (cooked.ingredientName
						.equals(ingredient.getIngredientName())) {
					((Vegetable) ingredient).cook(cooked.cookingWay);
					ingredient.setCookingWay(cooked.cookingWay);
				}
			}
		}
	}

	public void sortByName() {
		Collections.sort(ingredientList, new SortedByName());
	}

	public void sortByCalories() {
		Collections.sort(ingredientList, new SortedByCalories());
	}

	public int getCaloricContent() {
		int caloriContent = 0;
		for (Ingredient ingredient : ingredientList) {
			if ((ingredient instanceof MeasuredIngredient)) {
				caloriContent = caloriContent
						+ ((MeasuredIngredient) ingredient).getCalories();
			}
		}
		return caloriContent;
	}

	public List<Ingredient> getRange(int minCalories, int maxCalories) {

		List<Ingredient> range = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredientList) {
			if (ingredient instanceof Vegetable) {
				if ((((Vegetable) ingredient).getCalories() >= minCalories)
						& (((Vegetable) ingredient).getCalories() <= maxCalories)) {
					range.add(ingredient);
				}
			}
		}

		return range;
	}

	public SideDish clone() throws CloneNotSupportedException {
		SideDish cloneSideDish = (SideDish) (super.clone());
		cloneSideDish.sideDishName = this.getSideDishName() + " копия";
		List<Ingredient> ingredientListClone = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredientList) {
			ingredientListClone.add(ingredient.clone());
		}
		cloneSideDish.ingredientList = ingredientListClone;
		return cloneSideDish;
	}

	public String getDescription() {
		String descriptionString = this.sideDishName + "\n";
		for (Ingredient ingredient : ingredientList) {
			descriptionString += ingredient.getDescription() + "\n";
		}
		return descriptionString;

	}

	@Override
	public String toString() {
		return getDescription();

	}

	@Override
	public int compareTo(SideDish enterySideDish) {
		int caloricContent1 = this.getCaloricContent();
		int caloricContent2 = enterySideDish.getCaloricContent();
		if (caloricContent1 > caloricContent2) {
			return 1;
		} else if (caloricContent1 < caloricContent2) {
			return -1;
		}
		return 0;
	}

	public boolean equals(SideDish enterySideDish) {
		int caloricContent1 = this.getCaloricContent();
		int caloricContent2 = enterySideDish.getCaloricContent();
		if (caloricContent1 == caloricContent2) {
			return true;
		}
		return false;
	}

	public String getSideDishName() {
		return sideDishName;
	}

	public void setSideDishName(String sideDishName) {
		this.sideDishName = sideDishName;
	}

}
