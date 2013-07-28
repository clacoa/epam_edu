package com.epam.edu.proba;

public class SomeProperty {
	private Integer a = null;
	
	public SomeProperty(int value)
	{
	setA(new Integer(value));
	}
	public void setA(Integer a)
	{
	this.a = a;
	}
	public Integer getA()
	{
	return a;
	}
	
}
