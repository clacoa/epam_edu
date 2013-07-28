package com.epam.edu.oop.cookchief;

import com.epam.edu.oop.cookchief.dish.SideDish;
import com.epam.edu.oop.cookchief.util.PropertyReader;
import com.epam.edu.oop.cookchief.util.RegularFild;

public class Runner {
	public static void main(String[] args) throws CloneNotSupportedException {
		PropertyReader propertyReader = new PropertyReader();
		propertyReader.loadProperty(RegularFild.SIDEDISH1);
		SideDish sideDish = new SideDish(propertyReader.getProperties());
		SideDish copySideDish = sideDish.clone();
		// processing
		sideDish.cooking();
		// sideDish.sortByName();
		sideDish.sortByCalories();
		System.out.println(sideDish.toString());
		System.out.println(sideDish.getRange(RegularFild.MINCALORIES,
				RegularFild.MAXCALORIES).toString());
	}
}
