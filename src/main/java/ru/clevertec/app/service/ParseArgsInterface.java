package ru.clevertec.app.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;

import java.util.Optional;

public interface ParseArgsInterface {

	CustomList<CheckItem> getCheckItem(String[] args);

	Optional<Card> getCard(String[] args);

	int getPrintTo(String[] args);

}
