package com.check.app.entity;

public class Shop {
	private String name = "Krama N153";
	private String adress = "3rd Stroiteley Street, 25";
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
