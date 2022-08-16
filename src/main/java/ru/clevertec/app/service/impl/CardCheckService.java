package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.CheckRepository;
import ru.clevertec.app.repository.card.dbimpl.CardCheckRepositoryImpl;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidatorCard;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class CardCheckService implements CheckService<Card> {

    private final CheckRepository<Card> cardCheckRepositoryImpl;
    private final ValidatorCard validatorCard;

    @Override
    public Optional<Card> add(Card card) {
        if (!validatorCard.isValidParametersCard(card)) {
            return Optional.empty();
        }
        return Optional.of(cardCheckRepositoryImpl.add(card));
    }

    @Override
    public Optional<Card> update(Card card) {
        return Optional.empty();
    }

    @Override
    public Optional<Card> findById(String id) {
        if (!validatorCard.isValidIdCard(id)) {
            return Optional.empty();
        }
        return cardCheckRepositoryImpl.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Card> findAll(String limit, String offset) {
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;
        return cardCheckRepositoryImpl.findAll(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @Override
    public boolean delete(String id) {
        if (!validatorCard.isValidNumberCard(id)) {
            return false;
        }
        return cardCheckRepositoryImpl.delete(Long.parseLong(id));
    }
}
