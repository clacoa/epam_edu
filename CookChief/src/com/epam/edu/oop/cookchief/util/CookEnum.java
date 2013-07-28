package com.epam.edu.oop.cookchief.util;


public enum CookEnum {

	FRESH {
		@Override
		public String getDiscription() {
			return "";
		}
	},

	BOIL {
		@Override
		public String getDiscription() {
			return RegularFild.BOIL;
		}
	},

	FRY {
		@Override
		public String getDiscription() {
			return RegularFild.FRY;
		}
	},
	ROAST {
		@Override
		public String getDiscription() {
			return RegularFild.ROAST;
		}
	},
	MARINATE

	{
		@Override
		public String getDiscription() {
			return RegularFild.MARINATE;
		}
	};
	public abstract String getDiscription();
}
