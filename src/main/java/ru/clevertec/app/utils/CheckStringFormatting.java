package ru.clevertec.app.utils;

import ru.clevertec.app.entity.*;
import ru.clevertec.app.customlist.CustomList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckStringFormatting {
    private static final int LENGTH_CHECK = 50;

    private StringBuilder checkResult;

    public String getCheckResult(StringBuilder checkResult, StringBuilder checkError) {
        return checkError + (checkResult != null ? checkResult.toString() : "");
    }

    public StringBuilder getHeader(Shop shop, Cashier cashier, String date, String time) {
        checkResult = new StringBuilder();
        buildLine(checkResult);
        checkResult.append(String.format("%" + center(shop.getName().length()) + "s", shop.getName())).append("\n");
        checkResult.append(String.format("%" + center(shop.getAddress().length()) + "s", shop.getAddress())).append("\n");
        checkResult.append(String.format("кассир: # %-23s ", cashier.getNumber()));
        checkResult.append(String.format("Дата: %s", date)).append("\n");
        checkResult.append(String.format("%-34s", ""));
        checkResult.append(String.format("Время:%" + date.length() + "s", time)).append("\n");
        buildLine(checkResult);
        return checkResult;
    }

    public StringBuilder getBasket(CustomList<CheckItem> checkItems) {
        checkResult = new StringBuilder();
        checkResult.append(String.format("%-30s", "наименование"));
        checkResult.append(String.format("%-4s", "кол"));
        checkResult.append(String.format("%-9s", "цена"));
        checkResult.append(String.format("%7s", "всего")).append("\n");
        buildLine(checkResult);
        for (CheckItem ci : checkItems) {
            if (ci.getProduct().getName() == null) {
                checkResult.insert(0, "нет продукта с id: " + ci.getProduct().getId() + "\n");
            } else {
                checkResult.append(String.format("%-30s", ci.getProduct().getName()));
                checkResult.append(String.format("%-4s", ci.getQty()));
                checkResult.append(String.format("%-7s", ci.getProduct().getPrice() + "$"));
                checkResult.append(String.format("%9s", ci.getSumma() + "$")).append("\n");
                if (!ci.getDiscount().equals(BigDecimal.ZERO)) {
                    checkResult.append(String.format("%3s", " "));
                    checkResult.append(String.format("%-37s ", (ci.getPromDiscount() ? String.format("акция %s%% ", PropertiesUtil.get("ALL_DISCOUNT")) : "карта ") + "скидка:"));
                    checkResult.append(String.format("%10s", "-" + ci.getDiscount() + "$" + "\n"));
                    checkResult.append(String.format("%3s", " "));
                    checkResult.append(String.format("%-38s", "цена со скидкой:"));
                    checkResult.append(String.format("%10s", ci.getSumma().subtract(ci.getDiscount()) + "$" + "\n"));
                }
            }
        }
        buildLine(checkResult);
        buildLine(checkResult);
        return checkResult;
    }

    public StringBuilder getFooter(Card card, BigDecimal sumTotal, BigDecimal discountTotal) {
        checkResult = new StringBuilder();
        checkResult.append("Итого: ").append(sumTotal).append("$").append("\n");
        if (card != null) {
            checkResult.append("Дисконтная карта: ").append(card.getNumberCard()).append(" скидка: ")
                    .append(card.getDiscount().setScale(0, RoundingMode.HALF_DOWN)).append("%").append("\n");
        }
        if (discountTotal.compareTo(BigDecimal.ZERO) != 0) {
            checkResult.append("Скидка: -").append(discountTotal.setScale(2, RoundingMode.HALF_DOWN))
                    .append("$").append("\n");
            checkResult.append("Итого со скидкой: ")
                    .append(sumTotal.subtract(discountTotal.setScale(2, RoundingMode.HALF_DOWN))).append("$").append("\n");
        }
        buildLine(checkResult);
        return checkResult;
    }

    private void buildLine(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
    }

    private static int center(int i) {
        return (LENGTH_CHECK - i) / 2 + i;
    }
}
