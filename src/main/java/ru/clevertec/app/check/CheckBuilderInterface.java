package ru.clevertec.app.check;

import ru.clevertec.app.entity.Card;

import java.util.Map;

public interface CheckBuilderInterface {

    String getCheck(Map<Long, Integer> mapCheckItems, Card card);

}
