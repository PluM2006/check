package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;

public interface CheckInterface {

    String getCheck(CustomList<CheckItem> checkItems, Card card, Shop shop, Cashier cashier);

}
