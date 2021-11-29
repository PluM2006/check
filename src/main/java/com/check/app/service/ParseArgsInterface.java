package com.check.app.service;

import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;

public interface ParseArgsInterface {
	
	List<CheckItem> getCheckItem(String[] args);
	Card getCard(String[] args, String name);
	int getPrintTo(String[] args, String name);
	
	

}
