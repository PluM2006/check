package ru.clevertec.app.service;

import java.util.List;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Product;

public interface ReaderInterface {

	public List<Product> getAllProduct(String path);

	public List<Card> getAllCard(String path);

}
