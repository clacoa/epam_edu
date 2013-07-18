package com.epam.edu.matrix;

public class Matrix {
	private int rows;
	private int cols;
	private int internalMatrix[][];

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		internalMatrix = new int[rows][cols];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getItem(int row, int col) {
		return internalMatrix[row][col];
	}

	public void setItem(int row, int col, int value) {
		internalMatrix[row][col] = value;
	}

	public boolean isMultiplicateTo(Matrix m) {
		return cols == m.rows ? true : false;
	}

	public void fillRandom() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				internalMatrix[i][j] = (int) (Math.random() * Constants.RANGE);
			}
		}
	}

	public Matrix multiplicateTo(Matrix m) {
		Matrix multipResult;
		if ((m == null) || !isMultiplicateTo(m)) {
			return null;
		} else {
			multipResult = new Matrix(rows, m.getCols());
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < m.cols; j++) {
					int sum = 0;
					for (int k = 0; k < cols; k++) {
						sum += internalMatrix[i][k] * m.getItem(k,j);
					}
					multipResult.setItem(i, j, sum);
				}
			}
		}
		return multipResult;
	}
	
	public void showMatrix() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(internalMatrix[i][j]+"\t");
			}
			System.out.println();
		}
	}

}
