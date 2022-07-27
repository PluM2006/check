package ru.clevertec.app;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.dbImpl.CardRepositoryImpl;
import ru.clevertec.app.repository.dbImpl.CashierRepositoryImpl;
import ru.clevertec.app.repository.dbImpl.ShopRepositoryImpl;
import ru.clevertec.app.service.impl.CheckItemsDBImpl;
import ru.clevertec.app.service.impl.PrintToConsoleImpl;
import ru.clevertec.app.service.impl.PrintToFileImpl;
import ru.clevertec.app.service.interfaces.CheckBuilderInterface;
import ru.clevertec.app.service.interfaces.CheckItemsInterface;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.interfaces.PrintInterface;
import ru.clevertec.app.service.proxies.service.CheckBuilderImplProxy;
import ru.clevertec.app.service.utils.ArgsUtil;

public class CheckRunner {

    public static void main(String[] args) {
        PrintInterface print;
        CheckBuilderInterface checkImpl = new CheckBuilderImplProxy();
        //***If product and card in files

//        CheckItemsInterface checkItemsInterface = new CheckItemsFilesImpl();
//        Repository<Card> repository = new CardFileRepositoryImpl();

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
