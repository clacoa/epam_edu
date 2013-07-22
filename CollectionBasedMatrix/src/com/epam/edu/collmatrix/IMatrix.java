package com.epam.edu.collmatrix;

public interface IMatrix {

	public int getRows();

	public int getCols();

	public int getItem(int row, int col);

	public void setItem(int row, int col, int value);

	public boolean isMultiplicateTo(IMatrix m);

	public void fillRandom();

	public IMatrix multiplicateTo(IMatrix m);

	public void showMatrix();

}
