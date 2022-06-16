package ru.clevertec.app.service.impl;

import ru.clevertec.app.entity.*;
import ru.clevertec.app.service.CheckInteface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.ParseArgsInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckImpl implements CheckInteface {

    private final BigDecimal allDiscount = new BigDecimal(10);

    @Override
    public Check getCheck(String[] args) {
        Calendar cal = new GregorianCalendar();
        ParseArgsInterface parseArgs = new ParseArgsImpl();
        // Дисконтная карта
        Optional<Card> card = parseArgs.getCard(args, "card");
        // Продукты
        CustomList<CheckItem> checkItems = parseArgs.getCheckItem(args);
        Check check = new Check();
        check.setShop(new Shop("Krama N646", "3-я ул. Строителей, 25"));
        check.setCashier(new Cashier("Luke Skywalker", "007"));
        // куда вывод
        check.setPrintTo(parseArgs.getPrintTo(args, "printTo"));
        //Количество купленного товара
        Map<Product, Integer> productQty = checkItems.stream()
                .collect(Collectors.groupingBy(CheckItem::getProduct, Collectors.summingInt(CheckItem::getQty)));
        // Расчет скидок
        for (CheckItem checkItem : checkItems) {
            // Скидка 10% если товара больше 5
            Integer quantity = productQty.get(checkItem.getProduct());
            if (checkItem.getProduct().getSale() && quantity >= 5) {
                checkItem.setDiscount(getDiscount(checkItem.getSumm(), allDiscount));
                checkItem.setPromDiscount(true);
            } else {
                //Скидка на остальные товары если предъявлена дисконтная карта
                if (card.isPresent() && checkItem.getDiscount().equals(BigDecimal.ZERO)) {
                    checkItem.setDiscount(getDiscount(checkItem.getSumm(), card.get().getDiscount()));
                }
            }
        }
        check.setCheckItem(checkItems);
        check.setCard(card.orElse(null));
        check.setSummTotal(checkItems.stream().map(CheckItem::getSumm).reduce(BigDecimal.ZERO, BigDecimal::add));
        check.setDiscountTotal(checkItems.stream().map(CheckItem::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        check.setDate(getDateTime(cal, "dd-MM-YYYY"));
        check.setTime(getDateTime(cal, "HH:mm:ss"));
        return check;
    }

    private BigDecimal getDiscount(BigDecimal summ, BigDecimal percent) {
        BigDecimal discont;
        discont = percent.multiply(summ).divide(new BigDecimal(100), RoundingMode.HALF_DOWN)
                .setScale(2, RoundingMode.HALF_DOWN);
        return discont;
    }

    private String getDateTime(Calendar cal, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(cal.getTime());
    }

}