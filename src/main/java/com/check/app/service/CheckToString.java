package com.check.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;

public class CheckToString {

	private static final int LENGS_CHECK = 50;

	public String result(Check check) {
		StringBuilder result = new StringBuilder();
		long nullName = check.getCheckItem().stream().filter(p -> p.getProduct().getName() == null).count();
		if (nullName < check.getCheckItem().size()) {
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(
					String.format("%" + center(check.getShop().getName().length()) + "s", check.getShop().getName())
							+ "\n");
			result.append(
					String.format("%" + center(check.getShop().getAdress().length()) + "s", check.getShop().getAdress())
							+ "\n");
			result.append(String.format("%-34s", "кассир: #" + check.getCashier().getNumber()));
			result.append(String.format("%s", "Дата: " + check.getDate()) + "\n");
			result.append(String.format("%-34s", ""));
			result.append(String.format("Время:"));
			result.append(String.format("%" + check.getDate().length() + "s", check.getTime()) + "\n");
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(String.format("%-4s", "кол"));
			result.append(String.format("%-30s", "наименование"));
			result.append(String.format("%-9s", "цена"));
			result.append(String.format("%7s", "всего") + "\n");

			for (CheckItem ci : check.getCheckItem()) {
				if (ci.getProduct().getName() == null) {
					result.insert(0, "нет продукта с id: " + ci.getProduct().getId() + "\n");
				} else {
					result.append(String.format("%-4s", ci.getQty()));
					result.append(String.format("%-30s", ci.getProduct().getName()));
					result.append(String.format("%-9s", ci.getProduct().getPrice() + "$"));
					result.append(String.format("%7s", ci.getSumm() + "$") + "\n");
					if (ci.getPromDiscount()) {
						result.append(String.format("%6s", " "));
						result.append(String.format("%-37s", "скидка 10%:"));
						result.append(String.format("%8s", "-" + ci.getDiscount() + "$" + "\n"));
						result.append(String.format("%6s", " "));
						result.append(String.format("%-37s", "цена со скидкой:"));
						result.append(String.format("%8s", ci.getSumm().subtract(ci.getDiscount()) + "$" + "\n"));
					}
				}
			}

			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append("Итого: " + check.getSummTotal() + "$" + "\n");
			if (check.getCard() != null) {
				result.append("Дисконтная карта: " + check.getCard().getNumbercard() + " скидка: "
						+ check.getCard().getDiscount() + "%" + "\n");
			}
			if (check.getDiscountTotal().compareTo(BigDecimal.ZERO) != 0) {
				result.append("Скидка: -" + check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN) + "$" + "\n");
				result.append("Итого со скидкой: "
						+ check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))
						+ "\n");
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
