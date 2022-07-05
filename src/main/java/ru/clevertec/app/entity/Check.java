package ru.clevertec.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

import ru.clevertec.app.service.CustomList;

public class Check {

	private CustomList<CheckItem> checkItem;
	private Card card;
	private BigDecimal summTotal = BigDecimal.ZERO;
	private BigDecimal discountTotal = BigDecimal.ZERO;
	private Shop shop;
	private Cashier cashier;
	private String date;
	private String time;


	public Check() {
	}

	public Check(CustomList<CheckItem> checkItem, Card card, BigDecimal summTotal, BigDecimal discountTotal, Shop shop,
			Cashier cashier, String date, String time) {
		this.checkItem = checkItem;
		this.card = card;
		this.summTotal = summTotal;
		this.discountTotal = discountTotal;
		this.shop = shop;
		this.cashier = cashier;
		this.date = date;
		this.time = time;

	}

	public CustomList<CheckItem> getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(CustomList<CheckItem> checkItem) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Check check = (Check) o;
		return Objects.equals(checkItem, check.checkItem) && Objects.equals(card, check.card) && Objects.equals(summTotal, check.summTotal) && Objects.equals(discountTotal, check.discountTotal) && Objects.equals(shop, check.shop) && Objects.equals(cashier, check.cashier) && Objects.equals(date, check.date) && Objects.equals(time, check.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkItem, card, summTotal, discountTotal, shop, cashier, date, time);
	}

	@Override
	public String toString() {
		return "Check{" +
				"checkItem=" + checkItem +
				", card=" + card +
				", summTotal=" + summTotal +
				", discountTotal=" + discountTotal +
				", shop=" + shop +
				", cashier=" + cashier +
				", date='" + date + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}
