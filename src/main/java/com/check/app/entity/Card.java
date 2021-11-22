package com.check.app.entity;

public class Card {
	
	private Long id;
	private String numbercard;
	private Long discount;
	
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(Long id, String numbercard, Long discount) {
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

	public String getNumbercard() {
		return numbercard;
	}

	public void setNumbercard(String numbercard) {
		this.numbercard = numbercard;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	
	

}
