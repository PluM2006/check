package ru.clevertec.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {

	private Long id;
	private String numberCard;
	private BigDecimal discount;

	public Card() {
	}

	public Card(Long id, String numberCard, BigDecimal discount) {
		this.id = id;
		this.numberCard = numberCard;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
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
		return Objects.equals(id, card.id) && Objects.equals(numberCard, card.numberCard) && Objects.equals(discount, card.discount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numberCard, discount);
	}

	@Override
	public String toString() {
		return "Card{" + "id=" + id + ", numbercard='" + numberCard + '\'' + ", discount=" + discount + '}';
	}
}
