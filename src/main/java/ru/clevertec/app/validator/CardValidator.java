package ru.clevertec.app.validator;

import ru.clevertec.app.constant.ParameterNames;

import java.util.Map;

public class CardValidator {

    private static final String REGEX_ID = "[1-9]\\d*";
    private static final String REGEX_NUMBERCARD = "[1-9]([0-9]){3}-[0-9]{4}-[0-9]{4}-[0-9]{4}";
    private static final String REGEX_DISCOUNT = "([1-9][0-9]?)|100";

    public boolean isValidParametersCard(Map<String, String> parameters){
        boolean isValid = true;
        if (!isValidNumberCard(parameters.get(ParameterNames.CARD_NUMBERCARD))){
            isValid = false;
        }
        if (!isValidDiscount(parameters.get(ParameterNames.CARD_DISCOUNT))){
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidIdCard(String id){
        return id!=null && id.matches(REGEX_ID);
    }
    public boolean isValidNumberCard(String numberCard){
        return numberCard!=null && numberCard.matches(REGEX_NUMBERCARD);
    }
    public boolean isValidDiscount(String discount){
        return discount!=null && discount.matches(REGEX_DISCOUNT);
    }


}
