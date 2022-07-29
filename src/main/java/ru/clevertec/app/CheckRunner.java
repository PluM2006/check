package ru.clevertec.app;

import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.check.impl.PrintToConsoleImpl;
import ru.clevertec.app.check.impl.PrintToFileImpl;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.proxies.service.CheckBuilderImplProxy;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.card.dbimpl.CardRepositoryImpl;
import ru.clevertec.app.repository.shop.CashierRepositoryImpl;
import ru.clevertec.app.repository.shop.ShopRepositoryImpl;
import ru.clevertec.app.utils.ArgsUtil;

public class CheckRunner {

    public static void main(String[] args) {
        PrintInterface print;
        CheckBuilderInterface checkImpl = new CheckBuilderImplProxy();
//        //***If product and card in files
//
////        CheckItemsInterface checkItemsInterface = new CheckItemsFilesImpl();
////        Repository<Card> repository = new CardFileRepositoryImpl();
//
        CheckItemsInterface checkItemsInterface = new CheckItemsDBImpl();
        Repository<Card> repository = new CardRepositoryImpl();
        Repository<Cashier> cashierRepository = new CashierRepositoryImpl();
        Repository<Shop> shopRepository = new ShopRepositoryImpl();

        CustomList<CheckItem> checkItems = checkItemsInterface.getCheckItem(args);
        Card card = repository.findById(ArgsUtil.getInstance(args).getIdCard()).orElse(null);
        Cashier cashier = cashierRepository.findById(1L).orElse(null);
        Shop shop = shopRepository.findById(1L).orElse(null);
        int printTo = ArgsUtil.getInstance(args).getPrintTo();

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
