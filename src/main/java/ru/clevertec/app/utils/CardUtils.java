package ru.clevertec.app.utils;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.impl.CustomArrayList;

import java.math.BigDecimal;

public class CardUtils {

    public static CustomList<Card> createListCard() {
        CustomList<Card> cardCustomList = new CustomArrayList<>();
        cardCustomList.add(new Card(1L, "1111-1111-1111-1111", new BigDecimal(5)));
        cardCustomList.add(new Card(2L, "2222-2222-2222-2222", new BigDecimal(3)));
        cardCustomList.add(new Card(3L, "3333-3333-3333-3333", new BigDecimal(7)));
        cardCustomList.add(new Card(4L, "4444-4444-4444-4444", new BigDecimal(10)));
        return cardCustomList;
    }
}
