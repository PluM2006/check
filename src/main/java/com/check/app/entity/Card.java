package com.check.app.entity;

import java.math.BigDecimal;

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
	
	

}
