package com.check.app.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.check.app.entity.Card;
import com.check.app.entity.Product;
import com.check.app.service.ReaderInterface;

public class ReaderImpl implements ReaderInterface{

	@Override
	public List<Product> getAllProduct(String path) {
		List<Product> allProduct = new ArrayList<Product>();
		try (Scanner scanner = new Scanner(new File(path), "UTF-8");) {
		     while (scanner.hasNextLine()) {
		    	 allProduct.add(getProductFromLine(scanner.nextLine()));
		     }
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return allProduct;
	}

	@Override
	public List<Card> getAllCard(String path) {
		List<Card> allCard = new ArrayList<Card>();
		 try (Scanner scanner = new Scanner(new File(path), "UTF-8");) {
		     while (scanner.hasNextLine()) {
		    	 allCard.add(getCardFromLine(scanner.nextLine()));
		     }
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return allCard;
	}
	private Product getProductFromLine(String line) {
		Product values = new Product();
		String[] row = line.split(";"); 
		values.setId(Long.parseLong(row[0]));
		values.setName(row[1]);
		values.setPrice(new BigDecimal(row[2].replaceAll(",", ".")));
		values.setSale(Boolean.parseBoolean(row[3]));
	    return values;
	}
	private Card getCardFromLine(String line) {
		Card values = new Card();
		String[] row = line.split(";"); 
		values.setId(Long.parseLong(row[0]));
		values.setNumbercard(row[1]);
		values.setDiscount(new BigDecimal(row[2]));
		return values;
	}

}
