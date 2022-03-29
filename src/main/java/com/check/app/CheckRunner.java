package com.check.app;

import com.check.app.entity.Check;
import com.check.app.service.CheckInteface;
import com.check.app.service.PrintInterface;
import com.check.app.service.impl.CheckImpl;
import com.check.app.service.impl.PrintToConsoleImpl;
import com.check.app.service.impl.PrintToFileImpl;

public class CheckRunner {

	public static void main(String[] args) {
		CheckInteface checkImpl = new CheckImpl();
		Check check = checkImpl.getCheck(args);
		if (check.getPrintTo() == 0) {
			PrintInterface file = new PrintToFileImpl();
			file.print(check);
		}
		if (check.getPrintTo() == 1) {
			PrintInterface cons = new PrintToConsoleImpl();
			cons.print(check);
		}
		
	}
}
