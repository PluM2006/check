package com.check.app.entity;

public class Cashier {
	
	private String name = "Luke Skywalker";
	private String number = "000002";
	public Cashier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cashier(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
