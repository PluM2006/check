package ru.clevertec.app.service;

import ru.clevertec.app.entity.Check;
import ru.clevertec.app.entity.CheckItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckToString {

    private static final int LENGS_CHECK = 50;

    public String result(Check check) {
        StringBuilder result = new StringBuilder();
        long nullName = check.getCheckItem().stream().filter(p -> p.getProduct().getName() == null).count();
        if (nullName < check.getCheckItem().size()) {
            result.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
            result.append(String.format("%" + center(check.getShop().getName().length()) + "s", check.getShop()
                    .getName())).append("\n");
            result.append(String.format("%" + center(check.getShop().getAdress().length()) + "s", check.getShop()
                    .getAdress())).append("\n");
            result.append(String.format("%-34s", "кассир: #" + check.getCashier().getNumber()));
            result.append(String.format("%s", "Дата: " + check.getDate())).append("\n");
            result.append(String.format("%-34s", ""));
            result.append("Время:");
            result.append(String.format("%" + check.getDate().length() + "s", check.getTime())).append("\n");
            result.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
            result.append(String.format("%-4s", "кол"));
            result.append(String.format("%-30s", "наименование"));
            result.append(String.format("%-9s", "цена"));
            result.append(String.format("%7s", "всего")).append("\n");

			result.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
			result.append(String.format("%50s", " ").replace(" ", "-")).append("\n");
			result.append("Итого: ").append(check.getSummTotal()).append("$");
			if (check.getCard() != null) {
				result.append("\n").append("Дисконтная карта: ").append(check.getCard().getNumbercard()).append(" скидка: ")
						.append(check.getCard().getDiscount()).append("%").append("\n");
			}
			if (check.getDiscountTotal().compareTo(BigDecimal.ZERO) != 0) {
				result.append("Скидка: -").append(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))
						.append("$").append("\n");
				result.append("Итого со скидкой: ").append(check.getSummTotal()
						.subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)));
			}
		} else {
			result.append("Чек не сформирован" + "\n");
			result.append("Не указаны продукты, либо не правильный путь к файлу продуктов");
			for (CheckItem ci : check.getCheckItem()) {
				if (ci.getProduct().getName() == null) {
					result.insert(0, "нет продукта с id: " + ci.getProduct().getId() + "\n");
				}
			}
		}
		return result.toString();
	}

    private int center(int i) {
        return (LENGS_CHECK - i) / 2 + i;
    }
}
