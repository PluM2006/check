package ru.clevertec.app.entity;

import java.util.Objects;

public class Cashier {

	private String name;
	private String number;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cashier cashier = (Cashier) o;
		return Objects.equals(name, cashier.name) && Objects.equals(number, cashier.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, number);
	}

	@Override
	public String toString() {
		return "Cashier{" + "name='" + name + '\'' + ", number='" + number + '\'' + '}';
	}
}
