package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Product;

public interface ReaderInterface {

	public CustomList<Product> getAllProduct(String path);

	public CustomList<Card> getAllCard(String path);

}
