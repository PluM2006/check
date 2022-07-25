package ru.clevertec.app.service.impl;

import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.service.CheckFormatBuilder;
import ru.clevertec.app.service.interfaces.CheckInterface;
import ru.clevertec.app.service.interfaces.CustomList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CheckImpl implements CheckInterface {

    @Override
    public String getCheck(CustomList<CheckItem> checkItems, Card card, Shop shop, Cashier cashier) {
        if (checkItems.size() > 0) {
            buildHead(shop, cashier);
            getDiscount(checkItems, card);
            buildBasket(checkItems);
            buildFooter(card, checkItems);
        } else {
            CheckFormatBuilder.errorCheck();
        }
        return CheckFormatBuilder.getCheckResult();
    }

    private void buildHead(Shop shop, Cashier cashier) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        CheckFormatBuilder.getHeader(shop, cashier, date, time);
    }

    private void buildBasket(CustomList<CheckItem> checkItems) {
        CheckFormatBuilder.getBasket(checkItems);
    }

    private void buildFooter(Card card, CustomList<CheckItem> checkItems) {
        BigDecimal sumTotal = checkItems.stream().map(CheckItem::getSumm).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountTotal = checkItems.stream().map(CheckItem::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
        CheckFormatBuilder.getFooter(card, sumTotal, discountTotal);
    }

    private void getDiscount(CustomList<CheckItem> checkItems, Card card) {
        //Количество купленного товара
        Map<Product, Integer> productQty = checkItems.stream().collect(Collectors.groupingBy(CheckItem::getProduct, Collectors.summingInt(CheckItem::getQty)));
        // Расчет скидок
        for (CheckItem checkItem : checkItems) {
            // Скидка 10% если товара больше 5
            Integer quantity = productQty.get(checkItem.getProduct());
            if (checkItem.getProduct().getSale() && quantity >= 5) {
                checkItem.setDiscount(calculateDiscount(checkItem.getSumm(), new BigDecimal(Constants.ALL_DISCOUNT.getName())));
                checkItem.setPromDiscount(true);
            } else {
                //Скидка на остальные товары если предъявлена дисконтная карта
                if (card != null && checkItem.getDiscount().equals(BigDecimal.ZERO)) {
                    checkItem.setDiscount(calculateDiscount(checkItem.getSumm(), card.getDiscount()));
                }
            }
        }
    }

    private BigDecimal calculateDiscount(BigDecimal sum, BigDecimal percent) {
        BigDecimal discount;
        discount = percent.multiply(sum).divide(new BigDecimal(100), RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
        return discount;
    }
}
