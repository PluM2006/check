package com.check.app.service;

import com.check.app.entity.Card;
import com.check.app.entity.Product;

public interface ReaderInterface {

	public CustomList<Product> getAllProduct(String path);

	public CustomList<Card> getAllCard(String path);

}
