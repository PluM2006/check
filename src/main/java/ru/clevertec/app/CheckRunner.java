package ru.clevertec.app;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;
import ru.clevertec.app.service.PrintInterface;
import ru.clevertec.app.service.impl.ParseArgsImpl;
import ru.clevertec.app.service.impl.PrintToConsoleImpl;
import ru.clevertec.app.service.impl.PrintToFileImpl;
import ru.clevertec.app.service.proxies.service.CheckImplProxy;

public class CheckRunner {

    public static void main(String[] args) {

        CheckInterface checkImpl = new CheckImplProxy();

        ParseArgsInterface parseArgsInterface = new ParseArgsImpl();

        CustomList<CheckItem> checkItems = parseArgsInterface.getCheckItem(args);
        Card card = parseArgsInterface.getCard(args).orElse(null);
        int printTo = parseArgsInterface.getPrintTo(args);

        Shop shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        Cashier cashier = new Cashier("Luke Skywalker", "007");

        String check = checkImpl.getCheck(checkItems, card, shop, cashier);

		if (printTo == 0) {
			PrintInterface file = new PrintToFileImpl();
			file.print(check);
		}
		if (printTo == 1) {
			PrintInterface cons = new PrintToConsoleImpl();
			cons.print(check);
		}

    }
}
