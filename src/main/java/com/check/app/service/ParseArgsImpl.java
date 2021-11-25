package com.check.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;

public class ParseArgsImpl implements ParseArgsInterface{

	public static String fpath= "src/main/resources/";

	@Override
	public List<CheckItem> getCheckItem(String[] args) {
		ReaderInterface reader = new ReaderImpl();
		List<Product> allProduct = reader.getAllProduct(getPath(args, "fileProduction"));
		List<CheckItem> list = new ArrayList<CheckItem>();
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].matches("-?\\d+(\\.\\d+)?")) {
				Product product =allProduct.stream().filter(p->p.getId()==Integer.parseInt(a[0])).findAny().orElse(null); 
				CheckItem checkItem = new CheckItem(
						product, 
						Integer.parseInt(a[1]), 
						product.getPrice().multiply(new BigDecimal(a[1])), 
						null);
				list.add(checkItem);
			}
		}
		return list;
	}

	@Override
	public String getCard(String[] args, String name) {
		ReaderInterface reader = new ReaderImpl();
		List<Card> allCard = reader.getAllCard(getPath(args, "fileCard"));
		String card = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				card = a[1];
			} 
		}
		return card;
	}

	
	public String getPath(String[] args, String name) {
		String path = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				path = a[1];
			} 
		}
		if (path==null) {
			if (name.contains("fileProduction"))
				path = fpath+"product.csv";
			else 
				path = fpath+"card.csv";
		}
		return path;
	}

	
	
	
	

}
