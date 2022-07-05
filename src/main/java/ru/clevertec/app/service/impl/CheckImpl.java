package ru.clevertec.app.service.impl;

import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CheckImpl implements CheckInterface {
    private Card card;
    ParseArgsInterface parseArgs = new ParseArgsImpl();
    Calendar cal = new GregorianCalendar();

    @Override
    public Check getCheck(String[] args) {
        Check check = new Check();
        card = getCard(args);
        // Продукты
        CustomList<CheckItem> checkItems = parseArgs.getCheckItem(args);
        check.setShop(new Shop("Krama N646", "3-я ул. Строителей, 25"));
        check.setCashier(new Cashier("Luke Skywalker", "007"));
        // куда вывод
        check.setPrintTo(parseArgs.getPrintTo(args, Constants.PRINT_TO.getName()));
        calcDiscount(checkItems);
        check.setCheckItem(checkItems);
        check.setCard(card);
        check.setSummTotal(checkItems.stream().map(CheckItem::getSumm).reduce(BigDecimal.ZERO, BigDecimal::add));
        check.setDiscountTotal(checkItems.stream().map(CheckItem::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        check.setDate(getDateTime(Constants.DATE_FORMAT.getName()));
        check.setTime(getDateTime(Constants.TIME_FORMAT.getName()));
        return check;
    }

    private Card getCard(String[] args) {
        return parseArgs.getCard(args, Constants.CARD.getName()).orElse(null);
    }

    private void calcDiscount(CustomList<CheckItem> checkItems) {
        //Количество купленного товара
        Map<Product, Integer> productQty = checkItems.stream()
                .collect(Collectors.groupingBy(CheckItem::getProduct, Collectors.summingInt(CheckItem::getQty)));
        // Расчет скидок
        for (CheckItem checkItem : checkItems) {
            // Скидка 10% если товара больше 5
            Integer quantity = productQty.get(checkItem.getProduct());
            if (checkItem.getProduct().getSale() && quantity >= 5) {
                checkItem.setDiscount(getDiscount(checkItem.getSumm(), new BigDecimal(Constants.ALL_DISCOUNT.getName())));
                checkItem.setPromDiscount(true);
            } else {
                //Скидка на остальные товары если предъявлена дисконтная карта
                if (card != null && checkItem.getDiscount().equals(BigDecimal.ZERO)) {
                    checkItem.setDiscount(getDiscount(checkItem.getSumm(), card.getDiscount()));
                }
            }
        }
    }

    private BigDecimal getDiscount(BigDecimal sum, BigDecimal percent) {
        BigDecimal discount;
        discount = percent.multiply(sum).divide(new BigDecimal(100), RoundingMode.HALF_DOWN)
                .setScale(2, RoundingMode.HALF_DOWN);
        return discount;
    }

    private String getDateTime(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(cal.getTime());
    }

}
