package ru.clevertec.app.utils;

import ru.clevertec.app.constant.ConstantsFromArgs;

import java.util.HashMap;
import java.util.Map;

public class ArgsUtil {

    private static ArgsUtil INSTANCE;
    private static final Map<Long, Integer> mapCheckItems = new HashMap<>();
    private static Long idCard;
    private static int printTo = 0;

    private ArgsUtil(String[] args) {
        parseArgs(args);
    }

    public static ArgsUtil getInstance(String[] args) {
        if (INSTANCE == null) {
            INSTANCE = new ArgsUtil(args);
        }
        return INSTANCE;
    }

    public Map<Long, Integer> getMapCheckItems() {
        return mapCheckItems;
    }

    public Long getIdCard() {
        return idCard;
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
            if (argsLine[0].equals(ConstantsFromArgs.CARD.getName())) {
                if (argsLine[1].matches("\\d+")) {
                    idCard = Long.parseLong(argsLine[1]);
                }
            }
            if (argsLine[0].contains(ConstantsFromArgs.PRINT_TO.getName())) {
                printTo = Integer.parseInt(argsLine[1]);
            }
        }
    }
}
