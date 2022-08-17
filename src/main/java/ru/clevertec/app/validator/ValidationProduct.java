package ru.clevertec.app.validator;

import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.utils.YamlUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ValidationProduct {

    private static final String INCORRECT_INDEX =  "некорректный индекс";
    private static final String INCORRECT_NAME = "некорректное название";
    private static final String INCORRECT_PRICE =  "некорректная цена";
    private static final String INCORRECT_COUNT =  "некорректная количество";
    private static final String SPLITERATOR = ";";
    private static final String VERTICAL_LINE = " |";
    private static final String REGEX_ID = "[1-9][0-9]?[0-9]?[0-9]?|10000";
    private static final String REGEX_NAME = "[A-Z]([a-z]|\\s){2,29}|[А-Я]([а-я]|\\s){2,29}";
    private static final String REGEX_PRICE = "([1-9][0-9]?)[.,][0-9]{2}|100.00";
    private static final String REGEX_COUNT = "[1-9]|1[0-9]?|20";
    private final File file = new File(YamlUtils.getYamlProperties().getConstants().getPathInvalidFileName());
    private StringBuilder invalidDate = new StringBuilder();


    public boolean validParametersProduct(Product product) {
        var isValid = true;
        if (!isValidNameProduct(product.getName())) {
            isValid = false;
        }
        if (!isValidPriceProduct(product.getPrice())) {
            isValid = false;
        }
        if (!isValidCountProduct(product.getCount())) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidNameProduct(String name) {
        return name != null && name.matches(REGEX_NAME);
    }

    public boolean isValidPriceProduct(BigDecimal price) {
        return price != null;
    }

    public boolean isValidCountProduct(Integer count) {
        return count != null && (count >= 0);
    }

    public boolean isValidIdProduct(String id) {
        return id != null && id.matches(REGEX_ID);
    }

    public String getCorrectProduct(String line) {
        String resultLine = "";
        CustomList<String> listErrorMessage = validLineProduct(line);
        if (listErrorMessage.size() == 0) {
            resultLine = line;
        } else {
            buildInvalidMessage(line, listErrorMessage);
        }
        return resultLine;
    }

    public List<String> getCorrectProducts(List<String> lines) {
        List<String> resultList = new ArrayList<>();
        for (String line : lines) {
            CustomList<String> listErrorMessage = validLineProduct(line);
            if (listErrorMessage.size() == 0) {
                resultList.add(line);
            } else {
                buildInvalidMessage(line, listErrorMessage);
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(invalidDate.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private void buildInvalidMessage(String line, CustomList<String> listErrorMessage) {
        invalidDate = new StringBuilder();
        invalidDate.append(line);
        invalidDate.append(VERTICAL_LINE.trim());
        for (String errorMessage : listErrorMessage) {
            invalidDate.append(errorMessage);
            invalidDate.append(VERTICAL_LINE);
        }
        invalidDate.append("\n");
    }

    private CustomList<String> validLineProduct(String line) {
        CustomList<String> listErrorMessage = new CustomArrayList<>();
        String[] row = line.split(SPLITERATOR);
        if (!row[0].matches(REGEX_ID)) {
            listErrorMessage.add(INCORRECT_INDEX);
        }
        if (!row[1].matches(REGEX_NAME)) {
            listErrorMessage.add(INCORRECT_NAME);
        }
        if (!row[2].matches(REGEX_PRICE)) {
            listErrorMessage.add(INCORRECT_PRICE);
        }
        if (!row[3].matches(REGEX_COUNT)) {
            listErrorMessage.add(INCORRECT_COUNT);
        }
        return listErrorMessage;
    }

}
