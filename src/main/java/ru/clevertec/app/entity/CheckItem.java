package ru.clevertec.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CheckItem checkItem = (CheckItem) o;
		return Objects.equals(product, checkItem.product) && Objects.equals(qty, checkItem.qty) && Objects.equals(summ, checkItem.summ) && Objects.equals(discount, checkItem.discount) && Objects.equals(promDiscount, checkItem.promDiscount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, qty, summ, discount, promDiscount);
	}

	@Override
	public String toString() {
		return "CheckItem{" + "product=" + product + ", qty=" + qty + ", summ=" + summ + ", discount=" + discount + ", promDiscount=" + promDiscount + '}';
	}
}
