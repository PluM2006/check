package ru.clevertec.app.entity;

import java.util.Objects;

public class Cashier {

	private Long id;
	private String name;
	private String number;

	public Cashier() {
	}

	public Cashier(Long id, String name, String number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}
	public Cashier(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Objects.equals(id, cashier.id) && Objects.equals(name, cashier.name) && Objects.equals(number, cashier.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, number);
	}

	@Override
	public String toString() {
		return "Cashier{" +
				"id=" + id +
				", name='" + name + '\'' +
				", number='" + number + '\'' +
				'}';
	}
}
