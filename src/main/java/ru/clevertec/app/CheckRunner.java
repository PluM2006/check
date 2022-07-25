package ru.clevertec.app;

import ru.clevertec.app.entity.*;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.dbImpl.CardRepositoryImpl;
import ru.clevertec.app.repository.fileImpl.CardFileRepositoryImpl;
import ru.clevertec.app.service.ParseArgsService;
import ru.clevertec.app.service.interfaces.CheckInterface;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.interfaces.CheckItemsInterface;
import ru.clevertec.app.service.interfaces.PrintInterface;
import ru.clevertec.app.service.impl.CheckItemsFilesImpl;
import ru.clevertec.app.service.impl.PrintToConsoleImpl;
import ru.clevertec.app.service.impl.PrintToFileImpl;
import ru.clevertec.app.service.proxies.service.CheckImplProxy;

import java.util.Optional;

public class CheckRunner {

    public static String[] arg;

    public static void main(String[] args) {
        arg = args;
        PrintInterface print;
        CheckInterface checkImpl = new CheckImplProxy();
        CheckItemsInterface checkItemsInterface = new CheckItemsFilesImpl();
        Repository<Card> repository = new CardRepositoryImpl();
        Repository<Card> repositoryFile = new CardFileRepositoryImpl();
        repositoryFile.findById(3L);
        CustomList<CheckItem> checkItems = checkItemsInterface.getCheckItem(args);
//        Card card = parseArgsInterface.getCard(args).orElse(null);
        Optional<Card> card = repositoryFile.findById(ParseArgsService.getInstance(args).getIdCard());
        int printTo = ParseArgsService.getInstance(args).getPrintTo();

        Shop shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        Cashier cashier = new Cashier("Luke Skywalker", "007");

        String check = checkImpl.getCheck(checkItems, card.orElse(null), shop, cashier);

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
