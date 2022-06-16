package ru.clevertec.app.entity;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Shop shop = (Shop) o;
		return Objects.equals(name, shop.name) && Objects.equals(adress, shop.adress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, adress);
	}

	@Override
	public String toString() {
		return "Shop{" + "name='" + name + '\'' + ", adress='" + adress + '\'' + '}';
	}
}
