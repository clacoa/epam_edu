package com.epam.edu.chuggington;

public enum Direction {
	TO_MINES {
		@Override
		public String toString() {

			return "to mines";
		}
	},
	TO_DEPO {
		@Override
		public String toString() {

			return "to depo";
		}

	}
}
