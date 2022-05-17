package ru.clevertec.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.Check;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.service.CheckInteface;
import ru.clevertec.app.service.ParseArgsInterface;

public class CheckImpl implements CheckInteface {

	private final BigDecimal allDiscount = new BigDecimal(10);

	@Override
	public Check getCheck(String[] args) {

		ParseArgsInterface parseArgs = new ParseArgsImpl();
		// Дисконтная карта
		Card card = parseArgs.getCard(args, "card");
		// Продукты
		CustomList<CheckItem> checkItems = parseArgs.getCheckItem(args);
		Check check = new Check();
		check.setShop(new Shop("Krama N646", "3-я ул. Строителей, 25"));
		check.setCashier(new Cashier("Luke Skywalker", "007"));
		// куда вывод
		check.setPrintTo(parseArgs.getPrintTo(args, "printTo"));
		// Расчет скидок
		for (CheckItem checkItem : checkItems) {
			// Скидка 10% если товара больше 5
			Integer quantity = checkItems.stream().filter(p -> p.getProduct().getId() == checkItem.getProduct().getId())
					.map(x -> x.getQty()).reduce(0, Integer::sum);
			if (checkItem.getProduct().getSale() && quantity >= 5) {
				checkItem.setDiscount(getDiscount(checkItem.getSumm(), allDiscount));
				checkItem.setPromDiscount(true);
			} else {
				if (card != null && checkItem.getDiscount() == BigDecimal.ZERO) {
					checkItem.setDiscount(getDiscount(checkItem.getSumm(), card.getDiscount()));
				}
			}
		}
		check.setCheckItem(checkItems);
		check.setCard(card);
		check.setSummTotal(checkItems.stream().map(c -> c.getSumm()).reduce(BigDecimal.ZERO, BigDecimal::add));
		check.setDiscountTotal(checkItems.stream().map(c -> c.getDiscount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		Calendar cal = new GregorianCalendar();
		check.setDate(getDateTime(cal, "dd-MM-YYYY"));
		check.setTime(getDateTime(cal, "HH:mm:ss"));
		return check;
	}

	private BigDecimal getDiscount(BigDecimal summ, BigDecimal percent) {
		BigDecimal discont = BigDecimal.ZERO;
		discont = percent.multiply(summ).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
		return discont;
	}

	private String getDateTime(Calendar cal, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}

}
