package ru.clevertec.app.utils;

import ru.clevertec.app.entity.*;
import ru.clevertec.app.service.customlist.CustomList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckFormatBuilder {
    private static final int LENGTH_CHECK = 50;
    private static final StringBuilder checkErrors = new StringBuilder();
    private static final StringBuilder checkResult = new StringBuilder();

    public static String getCheckResult() {
        return checkErrors.toString() + checkResult;
    }

    public static void getHeader(Shop shop, Cashier cashier, String date, String time) {
        buildLine();
        checkResult.append(String.format("%" + center(shop.getName().length()) + "s", shop.getName())).append("\n");
        checkResult.append(String.format("%" + center(shop.getAddress().length()) + "s", shop.getAddress())).append("\n");
        checkResult.append(String.format("кассир: # %-23s ", cashier.getNumber()));
        checkResult.append(String.format("Дата: %s", date)).append("\n");
        checkResult.append(String.format("%-34s", ""));
        checkResult.append(String.format("Время:%" + date.length() + "s", time)).append("\n");
        buildLine();
    }

    public static void getBasket(CustomList<CheckItem> checkItems) {
        checkResult.append(String.format("%-30s", "наименование"));
        checkResult.append(String.format("%-4s", "кол"));
        checkResult.append(String.format("%-9s", "цена"));
        checkResult.append(String.format("%7s", "всего")).append("\n");
        buildLine();
        for (CheckItem ci : checkItems) {
            if (ci.getProduct().getName() == null) {
                checkResult.insert(0, "нет продукта с id: " + ci.getProduct().getId() + "\n");
            } else {
                checkResult.append(String.format("%-30s", ci.getProduct().getName()));
                checkResult.append(String.format("%-4s", ci.getQty()));
                checkResult.append(String.format("%-9s", ci.getProduct().getPrice() + "$"));
                checkResult.append(String.format("%7s", ci.getSumma() + "$")).append("\n");
                if (!ci.getDiscount().equals(BigDecimal.ZERO)) {
                    checkResult.append(String.format("%3s", " "));
                    checkResult.append(String.format("%-39s ", (ci.getPromDiscount() ? String.format("акция %s%% ", PropertiesUtil.get("ALL_DISCOUNT")) : "карта ")+"скидка:"));
                    checkResult.append(String.format("%8s", "-" + ci.getDiscount() + "$" + "\n"));
                    checkResult.append(String.format("%3s", " "));
                    checkResult.append(String.format("%-40s", "цена со скидкой:"));
                    checkResult.append(String.format("%8s", ci.getSumma().subtract(ci.getDiscount()) + "$" + "\n"));
                }
            }
        }
        buildLine();
        buildLine();
    }

    public static void getFooter(Card card, BigDecimal sumTotal, BigDecimal discountTotal) {
        checkResult.append("Итого: ").append(sumTotal).append("$").append("\n");
        if (card != null) {
            checkResult.append("Дисконтная карта: ").append(card.getNumberCard()).append(" скидка: ")
                    .append(card.getDiscount()).append("%").append("\n");
        }
        if (discountTotal.compareTo(BigDecimal.ZERO) != 0) {
            checkResult.append("Скидка: -").append(discountTotal.setScale(2, RoundingMode.HALF_DOWN))
                    .append("$").append("\n");
            checkResult.append("Итого со скидкой: ")
                    .append(sumTotal.subtract(discountTotal.setScale(2, RoundingMode.HALF_DOWN))).append("$").append("\n");
        }
        buildLine();
    }

    public static void errorCheckItems(CustomList<Long> errorItems) {
        for (Long ci : errorItems) {
            checkErrors.insert(0, "Нет продукта с id: " + ci + "\n");
        }
    }

    public static void errorCardFile() {
        checkErrors.append("Нет файла card.csv, либо не правильный путь к файлу дискотных карт" + "\n");
    }

    public static void errorProductFIle() {
        checkErrors.append("Нет файла product.csv, либо не правильный путь к файлу продуктов" + "\n");
    }

    public static void errorCheck() {
        checkErrors.append("Чек не сформирован" + "\n");
        checkErrors.append("Не указаны продукты");
    }

    private static void buildLine() {
        checkResult.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
    }


    private static int center(int i) {
        return (LENGTH_CHECK - i) / 2 + i;
    }
}
