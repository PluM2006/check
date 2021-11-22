package com.check.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckRunner {

	public static void main(String[] args) {
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
		try {
			listProduct = new ObjectMapper().readValue(isProduct, new TypeReference<List<Product>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		 Arrays.asList(args).stream().filter(o -> o.matches("card(.*)")).findAny().orElse(null);
		 List<CheckItem> listcheckitem = new ArrayList<CheckItem>();
		 List<Card> listCard = new ArrayList<Card>();
		 //Список продуктов чека
		 for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (!a[0].contains("card")) {
				Product prod = listProduct.stream().filter(p -> p.getId()==Integer.parseInt(a[0])).findAny().orElse(null);
				CheckItem item = new CheckItem(prod, Integer.parseInt(a[1]), new BigDecimal(0), new BigDecimal(0));
				listcheckitem.add(item);
			} else {
				Card ecard =listCardall.stream().filter(e -> e.getNumbercard().contains(a[1])).findAny().orElse(null);
				listCard.add(ecard);
			}
			
		}
		 if (listCard.size()>1) {
			 System.out.println("выберите нужную скидочную карту");
		 }
		 Product test = listProduct.stream().filter(prod -> "test".equals(prod.getName())).findAny().orElse(null);
//		System.out.println(test.getName());
		for (Card card : listCard) {
			System.out.println(card.getNumbercard());
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
