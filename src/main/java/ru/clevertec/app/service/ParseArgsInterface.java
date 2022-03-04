package ru.clevertec.app.service;

import java.util.List;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;

public interface ParseArgsInterface {

	List<CheckItem> getCheckItem(String[] args);

	Card getCard(String[] args, String name);

	int getPrintTo(String[] args, String name);

}
