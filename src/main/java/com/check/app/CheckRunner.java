package com.check.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckRunner {
	
	public static Product getRecordFromLine(String line) {
		Product values = new Product();
		System.out.println(line);
		String[] row = line.split(";"); 
		values.setId(Long.parseLong(row[0]));
		values.setName(row[1]);
		values.setPrice(new BigDecimal(row[2].replaceAll(",", ".")));
		values.setPromotion(Boolean.parseBoolean(row[3]));
		
//	    try (Scanner rowScanner = new Scanner(line)) {
//	        rowScanner.useDelimiter(",");
//	        while (rowScanner.hasNext()) {
////	            System.out.println(rowScanner.);
//	        }
//	    }
	    return values;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		 final BigDecimal discountp = new BigDecimal(10); 
		 InputStream isProduct = Product.class.getResourceAsStream("/product.json");
		 InputStream isCard = Card.class.getResourceAsStream("/card.json");
		 List<Card> listCardall = new ArrayList<Card>();
		try {
			listCardall = new ObjectMapper().readValue(isCard, new TypeReference<List<Card>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 List<Product> listProduct = new ArrayList<Product>();
		 
			/*
			 * try { listProduct = new ObjectMapper().readValue(isProduct, new
			 * TypeReference<List<Product>>(){}); } catch (IOException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
		 try (Scanner scanner = new Scanner(new File("src/main/resources/product.csv"), "UTF-8");) {
		     while (scanner.hasNextLine()) {
		    	 listProduct.add(getRecordFromLine(scanner.nextLine()));
		     }
		 }
		 
		 List<CheckItem> listcheckitem = new ArrayList<CheckItem>();
		 List<Card> listCard = new ArrayList<Card>();
		 
		 //**************************Входной массив (продукты карточки)
		 for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (!a[0].contains("card")) {
				Product prod = listProduct.stream().filter(p -> p.getId()==Integer.parseInt(a[0])).findAny().orElse(null);
				CheckItem item = new CheckItem(prod, Integer.parseInt(a[1]), prod.getPrice().multiply(new BigDecimal(a[1])), new BigDecimal(0));
				listcheckitem.add(item);
			} else {
				Card ecard =listCardall.stream().filter(e -> e.getNumbercard().contains(a[1])).findAny().orElse(null);
				listCard.add(ecard);
			}
			
		}
		 //*****************************************************************
		 Card card = new Card();
		 if (listCard.size()>1) {
			 int index = 1;
			 Scanner in = new Scanner(System.in);
			 for (Card c: listCard) {
				 System.out.println(c.getId()+" "+c.getNumbercard() );
				 index ++;
			 }
			 System.out.println("выберите нужную скидочную карту:");
			 int numcard = in.nextInt();
			 card = listCardall.stream().filter(e -> e.getId()==(numcard)).findAny().orElse(null);
			 System.out.println("карта :"+card.getNumbercard());
			 
			 
		 } else {
			 card = listCard.get(0);
		 }
		 //*********************нужная карта
		 
		 //печать чека
		 BigDecimal totalsumm = new BigDecimal(0);
		 BigDecimal totalsummdis = new BigDecimal(0);
		 int index = 1;
		 System.out.println(
				 	String.format("%-3s","QTY ")+
					String.format("%-15s", "DESCRIPION ")+
					String.format("%-6s", "PRICE ")+
					String.format("%-6s", "TOTAL "));
		 for (CheckItem cItem : listcheckitem) {
			 
			if (cItem.getProduct().getPromotion() && cItem.getNumber()>=5) {
				 cItem.setDiscontItem(cItem.getSummItem()
						 .subtract(discountp.multiply(cItem.getSummItem())
								 .divide(new BigDecimal(100))));
			}
			totalsumm = totalsumm.add(cItem.getSummItem());
			
			totalsummdis = totalsummdis.add(cItem.getDiscontItem());
			if (cItem.getDiscontItem().compareTo(BigDecimal.ZERO)==0) {
				if (card.getId()!=null) {
					totalsummdis = totalsummdis.add(cItem.getSummItem()
							.subtract(new BigDecimal(card.getDiscount())
									.multiply(cItem.getSummItem().divide(new BigDecimal(100)))));
				} else {
					totalsummdis = totalsummdis.add(cItem.getSummItem());
				}
			}
			
			System.out.println(
						String.format("%-3s", cItem.getNumber())+" "+
						String.format("%-15s", cItem.getProduct().getName())+
						String.format("%-6s", cItem.getProduct().getPrice()+"$ ")+
						String.format("%-6s", cItem.getSummItem()+"$ ")
						);
			if (cItem.getDiscontItem().compareTo(BigDecimal.ZERO)!=0) {
				System.out.println(
						String.format("%17s", "акция >5 скидка:")+
						String.format("%13s", cItem.getDiscontItem()+"$ ")
						);
			}
			index++;
		}
		System.out.println("Итого по чеку: "+totalsumm+"$");
		System.out.println("Итого по чеку со скидкой: "+totalsummdis.setScale(2));
		 
//		System.out.println(test.getName());
		for (Card c : listCard) {
//			System.out.println(card.getNumbercard());
		}
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
//		        System.out.println("File created: " + myObj.getName());
		      } else {
//		        System.out.println("File already exists.");
		      }
		      FileWriter myWriter = new FileWriter(myObj);
		      myWriter.write("Ага тут" + args[0]);
		      myWriter.close();
		    } catch (IOException e) {
//		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	

}
