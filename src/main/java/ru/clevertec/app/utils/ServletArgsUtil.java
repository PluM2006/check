package ru.clevertec.app.utils;

import ru.clevertec.app.constant.ConstantsFromArgs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServletArgsUtil {

    private static ServletArgsUtil INSTANCE;
    private static final Map<Long, Integer> mapCheckItems = new HashMap<>();
    private static Long idCard;

    private ServletArgsUtil(Map<String, String[]> parameterMap) {
        parseArgs(parameterMap);
    }

    public static ServletArgsUtil getInstance(Map<String, String[]> parameterMap) {
        if (INSTANCE == null) {
            INSTANCE = new ServletArgsUtil(parameterMap);
        }
        return INSTANCE;
    }

    public Map<Long, Integer> getMapCheckItems() {
        return mapCheckItems;
    }

    public Long getIdCard() {
        return idCard;
    }

    private static void parseArgs(Map<String, String[]> parameterMap) {
        System.out.println(Arrays.toString(parameterMap.get("id")));
        System.out.println(Arrays.toString(parameterMap.get("value")));
        String card = parameterMap.get("card") != null ? parameterMap.get("card")[0] : null;


        for (Map.Entry<String, String[]> parametr : parameterMap.entrySet()) {

        }


//        for (String arg : args) {
//            String[] argsLine = arg.split("-");
//            if (argsLine[0].matches("\\d+") && argsLine[1].matches("\\d+")) {
//                if (argsLine[0].matches("\\d+")) {
//                    long qty = Long.parseLong(argsLine[1]);
//                    if (argsLine[0].matches("-?\\d+(\\.\\d+)?") && qty > 0L) {
//                        mapCheckItems.put(Long.parseLong(argsLine[0]), Integer.parseInt(argsLine[1]));
//                    }
//                }
//            }
//            if (argsLine[0].equals(ConstantsFromArgs.CARD.getName())) {
//                if (argsLine[1].matches("\\d+")) {
//                    idCard = Long.parseLong(argsLine[1]);
//                }
//            }
//            if (argsLine[0].contains(ConstantsFromArgs.PRINT_TO.getName())) {
//                printTo = Integer.parseInt(argsLine[1]);
//            }
//        }
    }
}
