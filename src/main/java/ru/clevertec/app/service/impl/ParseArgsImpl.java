package ru.clevertec.app.service.impl;

import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;
import ru.clevertec.app.service.ReaderInterface;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;

public class ParseArgsImpl implements ParseArgsInterface {


    @Override
    public CustomList<CheckItem> getCheckItem(String[] args) {
        ReaderInterface reader = new ReaderImpl();
        CustomList<Product> allProduct = reader.getAllProduct(getPath(args, Constants.PRODUCT_FILE.getName()));
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        for (String arg : args) {
            String[] a = arg.split("-");
            if (a[1].matches("\\d")) {
                long qty = Long.parseLong(a[1]);
                if (a[0].matches("-?\\d+(\\.\\d+)?") && qty > 0L) {
                    Optional<Product> product = allProduct
                            .stream()
                            .filter(p -> p.getId() == Integer.parseInt(a[0]))
                            .findAny();
                    if (product.isPresent()) {
                        CheckItem checkItem = new CheckItem(product.get(), Integer.parseInt(a[1]), product
                                .get()
                                .getPrice()
                                .multiply(new BigDecimal(a[1])), BigDecimal.ZERO, false);
                        listCheckItem.add(checkItem);
                    }
                }
            }
        }
        return listCheckItem;
    }

    @Override
    public Optional<Card> getCard(String[] args) {
        ReaderInterface reader = new ReaderImpl();
        CustomList<Card> allCard = reader.getAllCard(getPath(args, Constants.CARD_FILE.getName()));
        Optional<Card> card = Optional.empty();
        for (String arg : args) {
            String[] a = arg.split("-");
            if (a[0].equals(Constants.CARD.getName())) {
                Long id = Long.parseLong(a[1]);
                card = allCard
                        .stream()
                        .filter(c -> c
                                .getNumbercard()
                                .equals(a[1]) || c
                                .getId()
                                .equals(id))
                        .findAny();
            }
        }
        return card;
    }

    @Override
    public int getPrintTo(String[] args) {
        int printTo = 0;
        for (String arg : args) {
            String[] a = arg.split("-");
            if (a[0].contains(Constants.PRINT_TO.getName())) {
                printTo = Integer.parseInt(a[1]);
            }
        }
        return printTo;
    }

    private String getPath(String[] args, String name) {
        String path = null;
        for (String arg : args) {
            String[] a = arg.split("-");
            if (a[0].contains(name)) {
                path = a[1];
            }
        }
        if (path == null || !new File(path).isFile()) {
            if (name.contains(Constants.PRODUCT.getName())) path = Constants.PATH_PRODUCT.getName();
            else path = Constants.PATH_CARD.getName();
        }
        return path;
    }

}
