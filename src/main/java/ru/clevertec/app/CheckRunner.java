package ru.clevertec.app;

import ru.clevertec.app.entity.*;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.dbImpl.CardRepositoryImpl;
import ru.clevertec.app.repository.dbImpl.ProductRepositoryImpl;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;
import ru.clevertec.app.service.PrintInterface;
import ru.clevertec.app.service.impl.ParseArgsImpl;
import ru.clevertec.app.service.impl.PrintToConsoleImpl;
import ru.clevertec.app.service.impl.PrintToFileImpl;
import ru.clevertec.app.service.proxies.service.CheckImplProxy;

import java.math.BigDecimal;

public class CheckRunner {

    public static void main(String[] args) {
        Card card1 = new Card(null, "5555 5555 5555 5555", BigDecimal.valueOf(8));


        Repository<Product> repository = new ProductRepositoryImpl();
        Repository<Card> cardRepository = new CardRepositoryImpl();
        System.out.println(cardRepository.findAll());

        PrintInterface print;
        CheckInterface checkImpl = new CheckImplProxy();
        ParseArgsInterface parseArgsInterface = new ParseArgsImpl();

        CustomList<CheckItem> checkItems = parseArgsInterface.getCheckItem(args);
        Card card = parseArgsInterface.getCard(args).orElse(null);
        int printTo = parseArgsInterface.getPrintTo(args);

        Shop shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        Cashier cashier = new Cashier("Luke Skywalker", "007");

        String check = checkImpl.getCheck(checkItems, card, shop, cashier);

        switch (printTo) {
            case 0 -> {
                print = new PrintToFileImpl();
                print.print(check);
            }
            case 1 -> {
                print = new PrintToConsoleImpl();
                print.print(check);
            }
        }
    }
}
