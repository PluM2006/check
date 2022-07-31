package ru.clevertec.app.service.impl;

import ru.clevertec.app.controller.ParameterNames;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.card.dbimpl.CardRepositoryImpl;
import ru.clevertec.app.repository.card.mapper.CardMapper;
import ru.clevertec.app.service.Service;
import ru.clevertec.app.validator.CardValidator;

import java.util.Map;
import java.util.Optional;

public class CardService implements Service<Card> {

    Repository<Card> cardRepository = new CardRepositoryImpl();

    CardValidator cardValidator = new CardValidator();
    @Override
    public Optional<Card> add(Map<String, String> parameters) {
        if (!cardValidator.isValidParametersCard(parameters)){
            return Optional.empty();
        }
        CardMapper cardMapper = new CardMapper(parameters);
        Card cardFromParameters = cardMapper.createCardFromParameters();
        return Optional.of(cardRepository.add(cardFromParameters));
    }

    @Override
    public Optional<Card> update(Map<String, String> parameters) {
        return Optional.empty();
    }

    @Override
    public Optional<Card> findById(String id) {
        return Optional.empty();
    }

    @Override
    public CustomList<Card> findAll(String limit, String offset) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
