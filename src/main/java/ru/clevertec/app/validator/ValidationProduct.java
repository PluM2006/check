package ru.clevertec.app.validator;

import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.utils.PropertiesUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ValidationProduct {

    private static final String REGEX_ID = "[1-9][0-9]?[0-9]?[0-9]?|10000";
    private static final String REGEX_NAME = "[A-Z]([a-z]|\\s){2,29}|[А-Я]([а-я]|\\s){2,29}";
    private static final String REGEX_PRICE = "([1-9][0-9]?)[.,][0-9]{2}|100.00";
    private static final String REGEX_COUNT = "[1-9]|1[0-9]?|20";
    private final File file = new File(PropertiesUtil.get("PATH_INVALID_FILE_NAME"));
    private final StringBuilder invalidDate = new StringBuilder();

    public String validParametersProduct(Map<String, String> parameters ){
        if (!parameters.get("name").matches(REGEX_NAME)) {
            invalidDate.append("некорректное название").append("|").append(parameters.get("name")).append(System.lineSeparator());
        }
        if (!parameters.get("price").matches(REGEX_PRICE)) {
            invalidDate.append("некорректная цена").append("|").append(parameters.get("price")).append(System.lineSeparator());
        }
        if (!parameters.get("count").matches(REGEX_COUNT)) {
            invalidDate.append("некорректное количество").append("|").append(parameters.get("count")).append(System.lineSeparator());
        }
//        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.UK);
//        String message = bundle.getString("label");
        return invalidDate.toString();
    }

    public String getCorrectProduct(String line){
        String resultLine = "";
        CustomList<String> listErrorMessage = validLineProduct(line);
        if (listErrorMessage.size() == 0) {
            resultLine = line;
        } else {
            buildInvalidMessage(line, listErrorMessage);
        }
        return resultLine;
    }

    public List<String> getCorrectProducts(List<String> lines){
        List<String> resultList = new ArrayList<>();
        for (String line: lines) {
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
    private void buildInvalidMessage(String line, CustomList<String> listErrorMessage){
        invalidDate.append(line);
        invalidDate.append(" |");
        for (String errorMessage : listErrorMessage) {
            invalidDate.append(errorMessage);
            invalidDate.append("|");
        }
        invalidDate.append("\n");
    }
    private CustomList<String> validLineProduct(String line){
        CustomList<String> listErrorMessage = new CustomArrayList<>();
        String[] row = line.split(";");
        if (!row[0].matches(REGEX_ID)) {
            listErrorMessage.add("некорректный индекс");
        }
        if (!row[1].matches(REGEX_NAME)) {
            listErrorMessage.add("некорректное название");
        }
        if (!row[2].matches(REGEX_PRICE)) {
            listErrorMessage.add("некорректная цена");
        }
        if (!row[3].matches(REGEX_COUNT)) {
            listErrorMessage.add("некорректное количество");
        }
        return listErrorMessage;
    }

}
