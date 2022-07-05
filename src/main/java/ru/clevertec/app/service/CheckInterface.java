package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;

public interface CheckInterface {

    String getCheck(CustomList<CheckItem> checkItems, Card card);

}
