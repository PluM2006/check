package ru.clevertec.app.validator;

import ru.clevertec.app.entity.Card;

import java.math.BigDecimal;

public class ValidatorCard {

    private static final String REGEX_ID = "[1-9]\\d*";
    private static final String REGEX_NUMBERCARD = "[1-9]([0-9]){3}-[0-9]{4}-[0-9]{4}-[0-9]{4}";
    private static final String REGEX_DISCOUNT = "([1-9][0-9]?)|100";

    public boolean isValidParametersCard(Card card) {
        boolean isValid = true;
        if (!isValidNumberCard(card.getNumberCard())) {
            isValid = false;
        }
        if (!isValidDiscount(card.getDiscount())) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidIdCard(String id) {
        return id != null && id.matches(REGEX_ID);
    }

    public boolean isValidNumberCard(String numberCard) {
        return numberCard != null && numberCard.matches(REGEX_NUMBERCARD);
    }

    public boolean isValidDiscount(BigDecimal discount) {
        return discount != null
                && (discount.compareTo(BigDecimal.ZERO) >= 0)
                && (discount.compareTo(BigDecimal.valueOf(100)) <= 0);
    }


}
