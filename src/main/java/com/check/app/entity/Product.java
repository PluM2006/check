package com.check.app.entity;

import java.math.BigDecimal;

public class Product {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private Boolean promotion;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, BigDecimal price, Boolean promotion) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.promotion = promotion;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getPromotion() {
		return promotion;
	}

	public void setPromotion(Boolean promotion) {
		this.promotion = promotion;
	}
	
	
}
