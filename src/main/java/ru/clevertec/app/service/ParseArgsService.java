package ru.clevertec.app.service;

import ru.clevertec.app.constant.Constants;

import java.util.HashMap;
import java.util.Map;

public class ParseArgsService {

    private static ParseArgsService instants;

    private static final Map<Long, Integer> mapCheckItems = new HashMap<>();
    private static Long idCard;
    private static String pathFileProduct;
    private static String pathFileCard;
    private static int printTo = 0;

    private ParseArgsService(String[] args) {
        parseArgs(args);
    }

    public static ParseArgsService getInstance(String[] args) {
        if (instants == null) {
            instants = new ParseArgsService(args);
        }
        return instants;
    }

    public Map<Long, Integer> getMapCheckItems() {
        return mapCheckItems;
    }

    public Long getIdCard() {
        return idCard;
    }

    public String getPathFileProduct() {
        return pathFileProduct;
    }

    public String getPathFileCard() {
        return pathFileCard;
    }

    public int getPrintTo() {
        return printTo;
    }

    private static void parseArgs(String[] args) {
        for (String arg : args) {
            String[] argsLine = arg.split("-");
            if (argsLine[0].matches("\\d+") && argsLine[1].matches("\\d+")) {
                if (argsLine[0].matches("\\d+")) {
                    long qty = Long.parseLong(argsLine[1]);
                    if (argsLine[0].matches("-?\\d+(\\.\\d+)?") && qty > 0L) {
                        mapCheckItems.put(Long.parseLong(argsLine[0]), Integer.parseInt(argsLine[1]));
                    }
                }
            }
            if (argsLine[0].equals(Constants.CARD.getName())) {
                if (argsLine[1].matches("\\d+")) {
                    idCard = Long.parseLong(argsLine[1]);
                }
            }
            if (argsLine[0].contains(Constants.PRODUCT_FILE.getName())) {
                pathFileProduct = argsLine[1];
            }
            if (argsLine[0].contains(Constants.CARD_FILE.getName())) {
                pathFileCard = argsLine[1];
            }
            if (argsLine[0].contains(Constants.PRINT_TO.getName())) {
                printTo = Integer.parseInt(argsLine[1]);
            }
        }
        if (pathFileProduct == null) {
            pathFileProduct = Constants.PATH_PRODUCT.getName();
        }
        if (pathFileCard == null) {
            pathFileCard = Constants.PATH_CARD.getName();
        }
    }
}
