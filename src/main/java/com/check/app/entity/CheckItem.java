package com.check.app.entity;

import java.math.BigDecimal;

public class CheckItem {

	private Product product;
	private Integer qty;
	private BigDecimal summ = BigDecimal.ZERO;
	private BigDecimal discount = BigDecimal.ZERO;
	private Boolean promDiscount = false;

	public CheckItem() {
	}

	public CheckItem(Product product, Integer qty, BigDecimal summ, BigDecimal discount, Boolean promDiscount) {
		this.product = product;
		this.qty = qty;
		this.summ = summ;
		this.discount = discount;
		this.promDiscount = promDiscount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getSumm() {
		return summ;
	}

	public void setSumm(BigDecimal summ) {
		this.summ = summ;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Boolean getPromDiscount() {
		return promDiscount;
	}

	public void setPromDiscount(Boolean promDiscount) {
		this.promDiscount = promDiscount;
	}

}
