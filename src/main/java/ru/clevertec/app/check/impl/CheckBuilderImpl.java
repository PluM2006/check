package ru.clevertec.app.check.impl;

import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.shop.CashierRepositoryImpl;
import ru.clevertec.app.repository.shop.ShopRepositoryImpl;
import ru.clevertec.app.utils.CheckErrorsStringFormatting;
import ru.clevertec.app.utils.CheckStringFormatting;
import ru.clevertec.app.utils.YamlUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckBuilderImpl implements CheckBuilderInterface {

    private final Repository<Cashier> cashierRepository = new CashierRepositoryImpl();
    private final Repository<Shop> shopRepository = new ShopRepositoryImpl();
    private final CheckItemsInterface checkItemsDB = CheckItemsDBImpl.getINSTANCE();
    private final CheckErrorsStringFormatting checkErrorsStringFormatting = new CheckErrorsStringFormatting();
    private final CheckStringFormatting checkStringFormatting = new CheckStringFormatting();

    @Override
    public String getCheck(Map<Long, Integer> mapCheckItems, Card card) {
        StringBuilder stringBuilderError = new StringBuilder();
        StringBuilder stringBuilderCheck = new StringBuilder();
        Cashier cashier = cashierRepository.findById(1L).orElse(null);
        Shop shop = shopRepository.findById(1L).orElse(null);
        CustomList<Long> errorsItem = new CustomArrayList<>();
        CustomList<CheckItem> checkItems = checkItemsDB.getCheckItem(mapCheckItems, errorsItem);
        if (checkItems.size() > 0) {
            stringBuilderCheck.append(buildHead(shop, cashier));
            getDiscount(checkItems, card);
            stringBuilderCheck.append(buildBasket(checkItems));
            stringBuilderCheck.append(buildFooter(card, checkItems));
        } else {
            checkErrorsStringFormatting.errorCheck(stringBuilderError);
        }
        if (errorsItem.size() > 0) checkErrorsStringFormatting.errorCheckItems(stringBuilderError, errorsItem);
        return checkStringFormatting.getCheckResult(stringBuilderCheck, stringBuilderError);
    }

    private StringBuilder buildHead(Shop shop, Cashier cashier) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return checkStringFormatting.getHeader(shop, cashier, date, time);
    }

    private StringBuilder buildBasket(CustomList<CheckItem> checkItems) {
        return checkStringFormatting.getBasket(checkItems);
    }

    private StringBuilder buildFooter(Card card, CustomList<CheckItem> checkItems) {
        BigDecimal sumTotal = checkItems.stream().map(CheckItem::getSumma).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountTotal = checkItems.stream().map(CheckItem::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return checkStringFormatting.getFooter(card, sumTotal, discountTotal);
    }

    private void getDiscount(CustomList<CheckItem> checkItems, Card card) {
        //Количество купленного товара
        Map<Product, Integer> productQty = checkItems.stream().collect(Collectors.groupingBy(CheckItem::getProduct, Collectors.summingInt(CheckItem::getQty)));
        // Расчет скидок
        for (CheckItem checkItem : checkItems) {
            // Скидка 10% если товара больше 5
            Integer quantity = productQty.get(checkItem.getProduct());
            if (checkItem.getProduct().getSale() && quantity >= 5) {
                checkItem.setDiscount(calculateDiscount(checkItem.getSumma(), new BigDecimal(YamlUtils.getYamlProperties().getConstants().getAllDiscount())));
                checkItem.setPromDiscount(true);
            } else {
                //Скидка на остальные товары если предъявлена дисконтная карта
                if (card != null && checkItem.getDiscount().equals(BigDecimal.ZERO)) {
                    checkItem.setDiscount(calculateDiscount(checkItem.getSumma(), card.getDiscount()));
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
