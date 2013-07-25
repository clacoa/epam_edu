package com.epam.edu.oop.cookchief;

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
			return Constant.BOIL;
		}
	},

	FRY {
		@Override
		public String getDiscription() {
			return Constant.FRY;
		}
	},
	ROAST {
		@Override
		public String getDiscription() {
			return Constant.ROAST;
		}
	},
	MARINATE

	{
		@Override
		public String getDiscription() {
			return Constant.MARINATE;
		}
	};
	public abstract String getDiscription();
}
