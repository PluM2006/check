package com.check.app;

import java.io.FileNotFoundException;

import com.check.app.entity.Check;
import com.check.app.service.CheckImpl;
import com.check.app.service.CheckInteface;
import com.check.app.service.PrintInterface;
import com.check.app.service.PrintToConsole;
import com.check.app.service.PrintToFile;

public class CheckRunner {
	
	public static void main(String[] args) throws FileNotFoundException {
		CheckInteface checkImpl = new CheckImpl();
		Check check = checkImpl.getCheck(args);
		PrintInterface file = new PrintToFile();
		PrintInterface cons = new PrintToConsole();
		cons.print(check);
		file.print(check);
	}
}

