package ru.clevertec.app;

import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.PrintInterface;
import ru.clevertec.app.service.impl.PrintToConsoleImpl;
import ru.clevertec.app.service.impl.PrintToFileImpl;
import ru.clevertec.app.service.proxies.service.CheckImplProxy;

public class CheckRunner {

	public static void main(String[] args) {
		CheckInterface checkImpl = new CheckImplProxy();
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
