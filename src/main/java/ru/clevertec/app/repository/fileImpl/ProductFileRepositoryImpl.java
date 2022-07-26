package ru.clevertec.app.repository.fileImpl;

import ru.clevertec.app.CheckRunner;
import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.service.CheckFormatBuilder;
import ru.clevertec.app.service.ArgsUtil;
import ru.clevertec.app.service.impl.CustomArrayList;
import ru.clevertec.app.service.interfaces.CustomList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ProductFileRepositoryImpl implements Repository<Product> {
    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CustomList<Product> findAll() {
        CustomList<Product> allProduct = new CustomArrayList<>();
        StringBuilder invalidDate = new StringBuilder();
        File file = new File(Constants.INVALID_FILE_NAME.getName());
        try (Scanner scanner = new Scanner(new File(ArgsUtil.getInstance(CheckRunner.arg).getPathFileProduct()), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                ArrayList<String> listErrorMessage = new ArrayList<>();
                String line = scanner.nextLine();
                String[] row = line.split(";");
                if (!row[0].matches("[1-9][0-9]?|100")) {
                    listErrorMessage.add("некорректный индекс");
                }
                if (!row[1].matches("[A-Z]([a-z]|\\s){2,29}|[А-Я]([а-я]|\\s){2,29}")) {
                    listErrorMessage.add("некорректное название");
                }
                if (!row[2].matches("([1-9][0-9]?)[.,][0-9]{2}|100.00")) {
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
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(invalidDate.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            CheckFormatBuilder.errorProductFIle();
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allProduct;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
