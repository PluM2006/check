package com.check.app.service;

import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;

public interface ParseArgsInterface {
	
	List<CheckItem> getCheckItem(String[] args);
	String getCard(String[] args, String name);
	
	

}
