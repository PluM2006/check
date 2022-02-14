package com.check.app.entity;

public class Shop {
	private String name;
	private String adress;

	public Shop(String name, String adress) {
		this.name = name;
		this.adress = adress;
	}

	public Shop() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
