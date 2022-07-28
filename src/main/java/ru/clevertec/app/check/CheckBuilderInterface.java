package ru.clevertec.app.check;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.customlist.CustomList;

public interface CheckBuilderInterface {

    String getCheck(CustomList<CheckItem> checkItems, Card card, Shop shop, Cashier cashier);

}
