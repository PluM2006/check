package com.check.app.entity;

public class Cashier {
	
	private String name = "Luke Skywalker";
	private String number = "007";
	public Cashier() {
			}
	public Cashier(String name, String number) {
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
