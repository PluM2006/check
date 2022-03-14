package com.check.app.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.check.app.entity.Card;
import com.check.app.entity.Product;
import com.check.app.service.ReaderInterface;

public class ReaderImpl implements ReaderInterface {

	@Override
	public List<Product> getAllProduct(String path) {
		List<Product> allProduct = new ArrayList<Product>();
		StringBuilder invalidDate = new StringBuilder();
		File file = new File("invalidDate.txt");
		try (Scanner scanner = new Scanner(new File(path), "UTF-8");) {
			while (scanner.hasNextLine()) {
				ArrayList<String> listErrorMessage = new ArrayList<>();
				String line = scanner.nextLine();
				String[] row = line.split(";");
				if (!row[0].matches("[1-9][0-9]?|100")) {
					listErrorMessage.add("некорректный индекс");
				}
				if (!row[1].matches("\\p{Lu}(\\p{Ll}|\s){2,29}")) {
					listErrorMessage.add("некорректное название");
				}
				if (!row[2].matches("([1-9][0-9]?)[.,][0-9]{2}||100.00")) {
					listErrorMessage.add("некорректная цена");
				}
				if (!row[3].matches("[1-9]|1[0-9]?|20")) {
					listErrorMessage.add("некорректное количество");
				}
				if (listErrorMessage.size() == 0) {
					Long id = Long.parseLong(row[0]);
					String name = row[1];
					BigDecimal price = new BigDecimal(row[2].replaceAll(",", "."));
					Integer count = Integer.parseInt(row[3]);
					Boolean sale = Boolean.parseBoolean(row[4]);
					allProduct.add(new Product(id, name, price, count, sale));
				} else {
					invalidDate.append(line);
					invalidDate.append(" |");
					for (String errorMessage : listErrorMessage) {
						invalidDate.append(errorMessage);
						invalidDate.append("|");
					}
					invalidDate.append("\n");
				}
			}
			try (FileWriter writer = new FileWriter(file);) {
				writer.write(invalidDate.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return allProduct;
	}

	@Override
	public List<Card> getAllCard(String path) {
		List<Card> allCard = new ArrayList<Card>();
		try (Scanner scanner = new Scanner(new File(path), "UTF-8");) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Card card = new Card();
				String[] row = line.split(";");
				card.setId(Long.parseLong(row[0]));
				card.setNumbercard(row[1]);
				card.setDiscount(new BigDecimal(row[2]));
				allCard.add(card);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return allCard;
	}
}
