package com.epam.edu.oop.cookchief;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.epam.edu.oop.cookchief.dish.SideDish;
import com.epam.edu.oop.cookchief.util.LogHandler;
import com.epam.edu.oop.cookchief.util.PropertyReader;
import com.epam.edu.oop.cookchief.util.RegularFild;

public class Runner {
	private static Logger log = Logger.getLogger(Runner.class.getName());

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException, ClassNotFoundException {

		log.addHandler(new LogHandler());
		log.setUseParentHandlers(false);
		log.info("The CookChif application is running");
		PropertyReader propertyReader = new PropertyReader();
		propertyReader.loadProperty(RegularFild.SIDEDISH1);
		SideDish sideDish = new SideDish("Салат 'Свежесть'",
				propertyReader.getProperties());
		log.info("Create an instance of the class SideDish");
		// Clone
		SideDish cloneSideDish = sideDish.clone();
		log.info("Create a clone an instance of the class SideDish");
		// processing
		cloneSideDish.cooking();
		sideDish.sortByName();
		// sideDish.sortByCalories();
		System.out.println(sideDish.toString());
		System.out.println(sideDish.getRange(RegularFild.MINCALORIES,
				RegularFild.MAXCALORIES).toString());
		System.out.println();
		System.out.println(cloneSideDish.toString());
		System.out.println(cloneSideDish.getRange(RegularFild.MINCALORIES,
				RegularFild.MAXCALORIES).toString());
		log.info("Console output Sidedishes and calorical");
		TreeSet<SideDish> caloricSortedMenu = new TreeSet<SideDish>();
		caloricSortedMenu.add(cloneSideDish);
		caloricSortedMenu.add(sideDish);
		System.out.println();
		System.out.println("Блюда отсортированные по калорийности:" + "/n");
		System.out.println();
		for (SideDish sideDish1 : caloricSortedMenu) {
			System.out.println(sideDish1.getSideDishName() + " "
					+ sideDish1.getCaloricContent() + " " + RegularFild.MEASUR);
		}

		// Serialize
		FileOutputStream fos = new FileOutputStream("temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(sideDish);
		oos.flush();
		oos.close();
		FileInputStream fis = new FileInputStream("temp.out");
		@SuppressWarnings("resource")
		ObjectInputStream oin = new ObjectInputStream(fis);
		SideDish deserialSideDish = (SideDish) oin.readObject();
		System.out.println();
		System.out.println("Сериализованный объект");
		System.out.println(deserialSideDish.toString());
		log.info("example serialization implemented");
	}
}
