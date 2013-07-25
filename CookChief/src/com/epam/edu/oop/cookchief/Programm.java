package com.epam.edu.oop.cookchief;

import com.epam.edu.oop.cookchief.dich.SideDish;
import com.epam.edu.oop.cookchief.ingredients.Ingredient;

public class Programm {
	public static void main(String[] args) {
		PropertyReader propertyReader = new PropertyReader();
		propertyReader.loadProperty(Constant.SIDEDISH1);
		//create sidedish
		SideDish sideDish = new SideDish(propertyReader.getProperties());
		//processing
	
		//sideDish.sortByName();
		sideDish.sortByCalories();
		System.out.println(sideDish.getDesription());
	}
}
