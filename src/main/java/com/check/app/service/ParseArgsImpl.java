package com.check.app.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;

public class ParseArgsImpl implements ParseArgsInterface{

	private static String productFile= "productFile";
	private static String cardFile= "cardFile";
	private static String pathFile= "product.csv";
	private static String pathCard= "card.csv";

	@Override
	public List<CheckItem> getCheckItem(String[] args) {
		ReaderInterface reader = new ReaderImpl();
		List<Product> allProduct = reader.getAllProduct(getPath(args, productFile));
		List<CheckItem> list = new ArrayList<CheckItem>();
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			Long qty = toLong(a[1]);
			if (a[0].matches("-?\\d+(\\.\\d+)?") && qty>0L) {
				Product product =allProduct.stream().filter(p->p.getId()==Integer.parseInt(a[0])).findAny().orElse(
						new Product(toLong(a[0]), null, BigDecimal.ZERO, false));
				CheckItem checkItem = new CheckItem(
						product, 
						Integer.parseInt(a[1]), 
						product.getPrice().multiply(new BigDecimal(a[1])), 
						BigDecimal.ZERO, false);
				
					list.add(checkItem);
			}
		}
		return list;
	}

	@Override
	public Card getCard(String[] args, String name) {
		ReaderInterface reader = new ReaderImpl();
		List<Card> allCard = reader.getAllCard(getPath(args, cardFile));
		Card card = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				Long id = toLong(a[1]);
				card = allCard.stream().filter(c -> c.getNumbercard().contains(a[1]) || c.getId()==id).findAny().orElse(card);
			} 
		}
		return card;
	}
	
	@Override
	public int getPrintTo(String[] args, String name) {
		int printTo = 0;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				printTo = toInt(a[1]);
			}
		}
		return printTo;
	}
	
	private Long toLong(String st) {
		Long i = 0L;
		try {
			i = Long.parseLong(st);
		} catch (Exception e) {
			// TODO: handle exception
			i = 0L;
		}
		return i;
	}
	
	private int toInt(String st) {
		int i = 0;
		try {
			i = Integer.parseInt(st);
		} catch (Exception e) {
			// TODO: handle exception
			i = 0;
		}
		return i;
	}
	
	private String getPath(String[] args, String name) {
		String path = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				path = a[1];
			}
		}
		if (path==null || !new File(path).isFile()) {
			if (name.contains("product"))
				path = pathFile;
			else 
				path = pathCard;
		}
		
		
		return path;
	}



	
	
	
	

}
