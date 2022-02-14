package com.check.app;

import com.check.app.entity.Check;
import com.check.app.service.CheckInteface;
import com.check.app.service.PrintInterface;
import com.check.app.service.PrintToConsole;
import com.check.app.service.PrintToFile;
import com.check.app.service.impl.CheckImpl;

public class CheckRunner {

	public static void main(String[] args) {
		CheckInteface checkImpl = new CheckImpl();
		Check check = checkImpl.getCheck(args);
		if (check.getPrintTo() == 0) {
			PrintInterface file = new PrintToFile();
			file.print(check);
		}
		if (check.getPrintTo() == 1) {
			PrintInterface cons = new PrintToConsole();
			cons.print(check);
		}

	}
}
