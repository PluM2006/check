package ru.clevertec.app.repository.card.mapper;

import ru.clevertec.app.controller.ParameterNames;
import ru.clevertec.app.entity.Card;

import java.math.BigDecimal;
import java.util.Map;

public class CardMapper {
    private final Map<String, String> parameters;

    public CardMapper(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Card createCardFromParameters() {
        Card card = new Card();
        card.setNumberCard(parameters.get(ParameterNames.CARD_NUMBERCARD));
        card.setDiscount(new BigDecimal(parameters.get(ParameterNames.CARD_DISCOUNT)));
        return card;
    }
}
