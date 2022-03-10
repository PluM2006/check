package com.check.app.entity;

import java.math.BigDecimal;

public class Product {

	private Long id;
	private String name;
	private BigDecimal price = BigDecimal.ZERO;
	private Integer qty;
	private Boolean sale = false;

	public Product() {
	}

	public Product(Long id, String name, BigDecimal price, Integer qty, Boolean sale) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.sale = sale;
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

	public Boolean getSale() {
		return sale;
	}

	public void setSale(Boolean sale) {
		this.sale = sale;
	}
	
	public Integer getQty() {
		return qty;
	}
	
	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
