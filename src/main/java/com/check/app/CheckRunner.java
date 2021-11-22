package com.check.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.check.app.entity.Product;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckRunner {
	
	//***
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		 InputStream inJson = Product[].class.getResourceAsStream("/product.json");
		 List<Product> participantJsonList = new ObjectMapper().readValue(inJson, new TypeReference<List<Product>>(){});
		 Product test = participantJsonList.stream().filter(prod -> "test".equals(prod.getName())).findAny().orElse(null);
		System.out.println(args[0]);
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		      FileWriter myWriter = new FileWriter(myObj);
		      myWriter.write("јга тут" + args[0]);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}

}
