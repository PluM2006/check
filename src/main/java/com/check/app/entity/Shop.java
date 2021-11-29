package com.check.app.entity;

public class Shop {
	private String name = "Krama N646";
	private String adress = "3-я ул. Строителей, 25";
	public Shop(String name, String adress) {
		super();
		this.name = name;
		this.adress = adress;
	}
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
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
