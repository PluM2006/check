package com.check.app.entity;

import java.math.BigDecimal;

public class CheckItem {
	
	private Product product;
	private Integer number;
	private BigDecimal summItem;
	private BigDecimal discontItem;
	
	public CheckItem(Product product, Integer number, BigDecimal summItem, BigDecimal discontItem) {
		super();
		this.product = product;
		this.number = number;
		this.summItem = summItem;
		this.discontItem = discontItem;
	}

	public CheckItem() {
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getSummItem() {
		return summItem;
	}

	public void setSummItem(BigDecimal summItem) {
		this.summItem = summItem;
	}

	public BigDecimal getDiscontItem() {
		return discontItem;
	}

	public void setDiscontItem(BigDecimal discontItem) {
		this.discontItem = discontItem;
	}
	
	
	

}
