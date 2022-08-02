package ru.clevertec.app.check;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.customlist.CustomList;

import java.util.Map;

public interface CheckBuilderInterface {

    String getCheck(Map<Long, Integer> mapCheckItems, Card card);

}
