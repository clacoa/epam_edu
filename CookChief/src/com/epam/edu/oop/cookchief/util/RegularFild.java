package com.epam.edu.oop.cookchief.util;

import java.util.ArrayList;
import java.util.List;

public class RegularFild {
	public static final String MEASUR = "ккал";
	public static final String SIDEDISH1 = "freshness.properties";

	public static final String BOIL = "отварить";
	public static final String FRY = "обжарить";
	public static final String ROAST = "запечь";
	public static final String MARINATE = "мариновать";
	
	public static final int MINCALORIES = 30;
	public static final int MAXCALORIES = 65;
	
	public static final List<CookingWay> Cooked(){
		List<CookingWay> cooked	= new ArrayList<CookingWay>();
		cooked.add(new CookingWay("картофель",
			CookEnum.FRY));
		cooked.add(new CookingWay("горошек",
			CookEnum.MARINATE));
		return cooked;
	}	
}