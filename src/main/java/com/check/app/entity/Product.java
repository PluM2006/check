package com.check.app.entity;

public class Product {
	
	private Long id;
	private String name;
	private Long price;
	private Integer discount;
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, Long price, Integer discount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	
}
