package com.epam.edu.oop.cookchief;

import java.util.TreeSet;

import com.epam.edu.oop.cookchief.dish.SideDish;
import com.epam.edu.oop.cookchief.util.PropertyReader;
import com.epam.edu.oop.cookchief.util.RegularFild;

public class Runner {
	public static void main(String[] args) throws CloneNotSupportedException {

		PropertyReader propertyReader = new PropertyReader();
		propertyReader.loadProperty(RegularFild.SIDEDISH1);
		SideDish sideDish = new SideDish("Салат 'Свежесть'",
				propertyReader.getProperties());
		SideDish copySideDish = sideDish.clone();
		// processing
		copySideDish.cooking();
		sideDish.sortByName();
		// sideDish.sortByCalories();
		System.out.println(sideDish.toString());
		System.out.println(sideDish.getRange(RegularFild.MINCALORIES,
				RegularFild.MAXCALORIES).toString());
		System.out.println();
		System.out.println(copySideDish.toString());
		System.out.println(copySideDish.getRange(RegularFild.MINCALORIES,
				RegularFild.MAXCALORIES).toString());

		TreeSet<SideDish> caloricSortedMenu = new TreeSet<SideDish>();
		caloricSortedMenu.add(copySideDish);
		caloricSortedMenu.add(sideDish);
		System.out.println();
		System.out.println("Блюда отсортированные по калорийности:" + "/n");
		System.out.println();
		for (SideDish sideDish1 : caloricSortedMenu) {
			System.out.println(sideDish1.getSideDishName() + " "
					+ sideDish1.getCaloricContent() + " " + RegularFild.MEASUR);
		}

	}
}
