package com.check.app.service;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;

public interface ParseArgsInterface {

	CustomList<CheckItem> getCheckItem(String[] args);

	Card getCard(String[] args, String name);

	int getPrintTo(String[] args, String name);

}
