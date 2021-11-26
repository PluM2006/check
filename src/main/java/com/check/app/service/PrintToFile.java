package com.check.app.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.check.app.entity.Check;

public class PrintToFile implements PrintInterface {

	@Override
	public void print(Check check) {
	
		CheckToString cts = new CheckToString();
		try {
		      File myObj = new File("check.txt");
		      FileWriter myWriter = new FileWriter(myObj);
		      myWriter.write(cts.result(check));	
		      myWriter.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}

}
