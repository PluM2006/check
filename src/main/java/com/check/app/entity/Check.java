package com.check.app.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

public class Check {
	
	private List<CheckItem> check;
	private Card card;
	private BigDecimal summTotal = BigDecimal.ZERO ;
	private BigDecimal discountTotal = BigDecimal.ZERO;
	private String name = "Krama";
	private String adress = "3rd Stroiteley Street, 25";
	private String dates;
	private String time;
	private Calendar date;
	
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Check(List<CheckItem> check, Card card, BigDecimal summTotal, BigDecimal discountTotal, String name, String adress,
			String dates, String time, Calendar date) {
		super();
		this.check = check;
		this.card = card;
		this.summTotal = summTotal;
		this.discountTotal = discountTotal;
		this.name = name;
		this.adress = adress;
		this.dates = dates;
		this.time = time;
		this.date = date;
	}
	public List<CheckItem> getCheck() {
		return check;
	}
	public void setCheck(List<CheckItem> check) {
		this.check = check;
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
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	

}
