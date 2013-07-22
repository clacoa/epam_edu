package com.epam.edu.collmatrix.impl;

import java.util.HashMap;
import java.util.Map;

import com.epam.edu.collmatrix.Constant;
import com.epam.edu.collmatrix.IMatrix;

public class Matrix implements IMatrix {

	private Map<MatrixElementPos, Integer> internalMatrix;
	private int rows;
	private int cols;

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		internalMatrix = new HashMap<MatrixElementPos, Integer>();
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getItem(int row, int col) {		
		return internalMatrix.get(new MatrixElementPos(col, row));
	}

	public void setItem(int row, int col, int value) {
		internalMatrix.put(new MatrixElementPos(col, row), value);

	}

	public boolean isMultiplicateTo(IMatrix m) {
		return this.cols == m.getRows() ? true : false;
	}

	public void fillRandom() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				MatrixElementPos pos=new MatrixElementPos(j, i);
				internalMatrix.put(pos,
						(int) (Math.random() * Constant.RANGE));
			}
		}
	}

	public IMatrix multiplicateTo(IMatrix m) {
		IMatrix multipResult;
		if ((m == null) || !isMultiplicateTo(m)) {
			return null;
		} else {
			multipResult = new Matrix(rows, m.getCols());
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < m.getCols(); j++) {
					int sum = 0;
					for (int k = 0; k < cols; k++) {
						sum += this.getItem(i, k) * m.getItem(k, j);
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
				System.out.print(this.getItem(i, j) + "\t");
			}
			System.out.println();
		}
	}

	public class MatrixElementPos {

		private int col;
		private int row;

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MatrixElementPos other = (MatrixElementPos) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		public MatrixElementPos(int col, int row) {
			this.col = col;
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		private Matrix getOuterType() {
			return Matrix.this;
		}
	}
}
