package ru.clevertec.app.service.interfaces;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.service.interfaces.CustomList;

public interface CheckInterface {

    String getCheck(CustomList<CheckItem> checkItems, Card card, Shop shop, Cashier cashier);

}
