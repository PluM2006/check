package com.check.app.service;

import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.Product;

public interface ReaderInterface {

	public List<Product> getAllProduct(String path);

	public List<Card> getAllCard(String path);

}
