package ru.clevertec.app;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;
import ru.clevertec.app.service.impl.ParseArgsImpl;
import ru.clevertec.app.service.proxies.service.CheckImplProxy;

public class CheckRunner {

	public static void main(String[] args) {
		CheckInterface checkImpl = new CheckImplProxy();

		ParseArgsInterface parseArgsInterface = new ParseArgsImpl();

		CustomList<CheckItem> checkItems = parseArgsInterface.getCheckItem(args);
		Card card = parseArgsInterface.getCard(args).orElse(null);

		String check = checkImpl.getCheck(checkItems, card);




//		if (check.getPrintTo() == 0) {
//			PrintInterface file = new PrintToFileImpl();
//			file.print(check);
//		}
//		if (check.getPrintTo() == 1) {
//			PrintInterface cons = new PrintToConsoleImpl();
//			cons.print(check);
//		}

	}
}
