package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;

public interface ParseArgsInterface {

	CustomList<CheckItem> getCheckItem(String[] args);

	Card getCard(String[] args, String name);

	int getPrintTo(String[] args, String name);

}
