package com.check.app.entity;

import java.math.BigDecimal;

public class CheckItem {
	
	private Product product;
	private Integer qty;
	private BigDecimal summ;
	private BigDecimal discont;
	
	public CheckItem() {
		// TODO Auto-generated constructor stub
	}
	
	public CheckItem(Product product, Integer qty, BigDecimal summ, BigDecimal discont) {
		super();
		this.product = product;
		this.qty = qty;
		this.summ = summ;
		this.discont = discont;
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

	public BigDecimal getDiscont() {
		return discont;
	}

	public void setDiscont(BigDecimal discont) {
		this.discont = discont;
	}
	

}
