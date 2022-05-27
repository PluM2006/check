package ru.clevertec.app.service.impl;

import java.io.File;
import java.math.BigDecimal;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.ParseArgsInterface;
import ru.clevertec.app.service.ReaderInterface;
import ru.clevertec.app.service.CustomList;

public class ParseArgsImpl implements ParseArgsInterface {

	private static final String PRODUCT_FILE = "productFile";
	private static final String CARD_FILE = "cardFile";
	private static final String PATH_PRODUCT = "product.csv";
	private static final String PATH_CARD = "card.csv";

	@Override
	public CustomList<CheckItem> getCheckItem(String[] args) {
		ReaderInterface reader = new ReaderImpl();
		CustomList<Product> allProduct = reader.getAllProduct(getPath(args, PRODUCT_FILE));
		CustomList<CheckItem> listCheckItem = new CustomArrayList<CheckItem>();
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			long qty;
			if (a[0].matches("\\d+")) {
				qty = Long.parseLong(a[1]);
			} else {
				break;
			}
			if (a[0].matches("-?\\d+(\\.\\d+)?") && qty > 0L) {
				Product product = allProduct.stream().filter(p -> p.getId() == Integer.parseInt(a[0])).findAny()
						.orElse(new Product(Long.parseLong(a[0]), null, BigDecimal.ZERO, 0, false));
				CheckItem checkItem = new CheckItem(product, Integer.parseInt(a[1]),
						product.getPrice().multiply(new BigDecimal(a[1])), BigDecimal.ZERO, false);

				listCheckItem.add(checkItem);
			}
		}
		return listCheckItem;
	}

	@Override
	public Card getCard(String[] args, String name) {
		ReaderInterface reader = new ReaderImpl();
		CustomList<Card> allCard = reader.getAllCard(getPath(args, CARD_FILE));
		Card card = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].equals(name)) {
				Long id = Long.parseLong(a[1]);
				card = allCard.stream().filter(c -> c.getNumbercard().contains(a[1]) || c.getId() == id).findAny()
						.orElse(card);
			}
		}
		return card;
	}

	@Override
	public int getPrintTo(String[] args, String name) {
		int printTo = 0;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				printTo = Integer.parseInt(a[1]);
			}
		}
		return printTo;
	}

	private String getPath(String[] args, String name) {
		String path = null;
		for (int i = 0; i < args.length; i++) {
			String[] a = args[i].split("-");
			if (a[0].contains(name)) {
				path = a[1];
			}
		}
		if (path == null || !new File(path).isFile()) {
			if (name.contains("product"))
				path = PATH_PRODUCT;
			else
				path = PATH_CARD;
		}
		return path;
	}

}
