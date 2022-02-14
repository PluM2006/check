package com.check.app.entity;

import java.math.BigDecimal;
import java.util.List;

public class Check {

	private List<CheckItem> checkItem;
	private Card card;
	private BigDecimal summTotal = BigDecimal.ZERO;
	private BigDecimal discountTotal = BigDecimal.ZERO;
	private Shop shop;
	private Cashier cashier;
	private String date;
	private String time;
	private int printTo;

	public Check() {
	}

	public Check(List<CheckItem> checkItem, Card card, BigDecimal summTotal, BigDecimal discountTotal, Shop shop,
			Cashier cashier, String date, String time, int printTo) {
		this.checkItem = checkItem;
		this.card = card;
		this.summTotal = summTotal;
		this.discountTotal = discountTotal;
		this.shop = shop;
		this.cashier = cashier;
		this.date = date;
		this.time = time;
		this.printTo = printTo;
	}

	public List<CheckItem> getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(List<CheckItem> checkItem) {
		this.checkItem = checkItem;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public BigDecimal getSummTotal() {
		return summTotal;
	}

	public void setSummTotal(BigDecimal summTotal) {
		this.summTotal = summTotal;
	}

	public BigDecimal getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(BigDecimal discountTotal) {
		this.discountTotal = discountTotal;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPrintTo() {
		return printTo;
	}

	public void setPrintTo(int printTo) {
		this.printTo = printTo;
	}

}
