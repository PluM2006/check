package ru.clevertec.app.service.impl;

import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.card.dbimpl.CardRepositoryImpl;
import ru.clevertec.app.service.Service;
import ru.clevertec.app.validator.CardValidator;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

public class CardService implements Service<Card> {

    private static final CardService INSTANCE = new CardService();

    private final Repository<Card> cardRepository = new CardRepositoryImpl();

    private final CardValidator cardValidator = new CardValidator();

    @Override
    public Optional<Card> add(Card card) {
        if (!cardValidator.isValidParametersCard(card)) {
            return Optional.empty();
        }
        return Optional.of(cardRepository.add(card));
    }

    @Override
    public Optional<Card> update(Card card) {
        return Optional.empty();
    }

    @Override
    public Optional<Card> findById(String id) {
        if (!cardValidator.isValidIdCard(id)) {
            return Optional.empty();
        }
        return cardRepository.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Card> findAll(String limit, String offset) {
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;

        return cardRepository.findAll(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @Override
    public boolean delete(String id) {
        if (!cardValidator.isValidNumberCard(id)) {
            return false;
        }
        return cardRepository.delete(Long.parseLong(id));
    }

    public static CardService getInstance() {
        return INSTANCE;
    }
}
