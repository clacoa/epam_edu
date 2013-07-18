package com.epam.edu.matrix;

import java.lang.Math;

public class Program {
	public static void main(String[] args) {
		System.out.println("Start");
		System.out.println();
		int cols1 = Constants.COL1;
		int rows1 = (int) (Math.random() * Constants.RANGE) + 1;
		int rows2 = Constants.ROW2;
		int cols2 = (int) (Math.random() * Constants.RANGE) + 1;
		Matrix m1 = new Matrix(rows1, cols1);
		Matrix m2 = new Matrix(rows2, cols2);
		System.out.println("M1 [" + rows1 + ";" + cols1 + "]");
		m1.fillRandom();
		m1.showMatrix();
		System.out.println();
		System.out.println("M2 [" + rows2 + ";" + cols2 + "]");
		m2.fillRandom();
		m2.showMatrix();
		
		Matrix m3 = m1.multiplicateTo(m2);
		if (m3 != null) {
			System.out.println();
			System.out.println("M3 [" + m3.getRows() + ";" + m3.getCols() + "]");
			m3.showMatrix();
		} else {
			System.out.println(" Multiplication not possible");
		}
		System.out.println("End");
	}
}
