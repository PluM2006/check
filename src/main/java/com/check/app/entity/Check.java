package com.check.app.entity;

import java.math.BigDecimal;
import java.util.List;

public class Check {
	
	private List<CheckItem> checkItem;
	private Card card;
	private BigDecimal summTotal = BigDecimal.ZERO ;
	private BigDecimal discountTotal = BigDecimal.ZERO;
	private String name = "Krama";
	private String adress = "3rd Stroiteley Street, 25";
	private String date;
	private String time;
	
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Check(List<CheckItem> checkItem, Card card, BigDecimal summTotal, BigDecimal discountTotal, String name, String adress,
			String date, String time) {
		super();
		this.checkItem = checkItem;
		this.card = card;
		this.summTotal = summTotal;
		this.discountTotal = discountTotal;
		this.name = name;
		this.adress = adress;
		this.date = date;
		this.time = time;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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

}
