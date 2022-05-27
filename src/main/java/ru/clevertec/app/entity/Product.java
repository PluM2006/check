package ru.clevertec.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

	private Long id;
	private String name;
	private BigDecimal price = BigDecimal.ZERO;
	private Integer count;
	private Boolean sale = false;

	public Product() {
	}

	public Product(Long id, String name, BigDecimal price, Integer count, Boolean sale) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(count, product.count) && Objects.equals(sale, product.sale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, count, sale);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", count=" + count + ", sale=" + sale
				+ "]";
	}

}
