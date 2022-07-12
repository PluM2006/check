package ru.clevertec.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {


	private Long id;
	private String numbercard;
	private BigDecimal discount;

	public Card() {
	}

	public Card(Long id, String numbercard, BigDecimal discount) {
		this.id = id;
		this.numbercard = numbercard;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumbercard() {
		return numbercard;
	}

	public void setNumbercard(String numbercard) {
		this.numbercard = numbercard;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Objects.equals(id, card.id) && Objects.equals(numbercard, card.numbercard) && Objects.equals(discount, card.discount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numbercard, discount);
	}

	@Override
	public String toString() {
		return "Card{" + "id=" + id + ", numbercard='" + numbercard + '\'' + ", discount=" + discount + '}';
	}
}
