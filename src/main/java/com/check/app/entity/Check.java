package com.check.app.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

public class Check {
	
	private List<CheckItem> check;
	private BigDecimal summTotal = BigDecimal.ZERO ;
	private BigDecimal discountTotal = BigDecimal.ZERO;
	private String name;
	private String adress;
	private String dates;
	private String time;
	private Calendar date;
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Check(List<CheckItem> check, BigDecimal summTotal, BigDecimal discountTotal) {
		super();
		this.check = check;
		this.summTotal = summTotal;
		this.discountTotal = discountTotal;
	}
	public List<CheckItem> getCheck() {
		return check;
	}
	public void setCheck(List<CheckItem> check) {
		this.check = check;
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
	
	

}
