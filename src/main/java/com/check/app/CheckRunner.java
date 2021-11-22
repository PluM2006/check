package com.check.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
		 List<Card> listCard = new ArrayList<Card>();
		 List<CheckItem> listcheckitem = new ArrayList<CheckItem>();
		try {
			listCard = new ObjectMapper().readValue(isCard, new TypeReference<List<Card>>(){});
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
		 Arrays.asList(args).stream().filter(o -> o.matches("card(.*)")).findAny().orElse(null);
		 
		 for (int i = 0; i < args.length; i++) {
			String[] test = args[i].split("-");
			
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
		      myWriter.write("јга тут" + args[0]);
		      myWriter.close();
		    } catch (IOException e) {
//		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}

}
