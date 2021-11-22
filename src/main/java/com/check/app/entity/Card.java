package com.check.app.entity;

public class Card {
	
	private Long id;
	private Long numbercard;
	private Long discount;
	
	public Card(Long id, Long numbercard, Long discount) {
		super();
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

	public Long getNumbercard() {
		return numbercard;
	}

	public void setNumbercard(Long numbercard) {
		this.numbercard = numbercard;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	
	

}
