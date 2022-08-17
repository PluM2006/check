package ru.clevertec.app;

import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.check.impl.PrintToConsoleImpl;
import ru.clevertec.app.check.impl.PrintToFileImpl;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.proxies.service.CheckBuilderImplProxy;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.card.dbimpl.CardRepositoryImpl;
import ru.clevertec.app.utils.ArgsUtil;

import java.util.Map;

public class CheckRunner {

    public static void main(String[] args) {
        PrintInterface print;
        CheckBuilderInterface checkImpl = new CheckBuilderImplProxy();
//        //***If product and card in files
//
////        CheckItemsInterface checkItemsInterface = new CheckItemsFilesImpl();
////        Repository<Card> repository = new CardFileRepositoryImpl();
//
        Map<Long, Integer> mapCheckItems = ArgsUtil.getInstance(args).getMapCheckItems();
        Repository<Card> repository = new CardRepositoryImpl();
        Card card = repository.findById(ArgsUtil.getInstance(args).getIdCard()).orElse(null);

        int printTo = ArgsUtil.getInstance(args).getPrintTo();

        String check = checkImpl.getCheck(mapCheckItems, card);

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
