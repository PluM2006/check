package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Product;

public interface ReaderInterface {

	CustomList<Product> getAllProduct(String path);

	CustomList<Card> getAllCard(String path);

}
