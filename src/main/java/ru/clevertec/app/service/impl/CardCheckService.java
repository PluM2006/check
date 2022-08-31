package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.CardRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidatorCard;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class CardCheckService implements CheckService<Card> {

    private final CardRepository cardRepository;
    private final ValidatorCard validatorCard;

    @Override
    public Optional<Card> add(Card card) {
        if (!validatorCard.isValidParametersCard(card)) {
            return Optional.empty();
        }
        return Optional.of(cardRepository.save(card));
    }

    @Override
    public Optional<Card> update(Card card) {
        if (!validatorCard.isValidParametersCard(card)) {
            return Optional.empty();
        }
        return Optional.of(cardRepository.save(card));
    }

    @Override
    public Optional<Card> findById(String id) {
        if (!validatorCard.isValidIdCard(id)) {
            return Optional.empty();
        }
        return cardRepository.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Card> findAll() {
        CustomList<Card> cards = new CustomArrayList<>();
        cardRepository.findAll().forEach(cards::add);
        return cards;
    }

    @Override
    public CustomList<Card> findAll(String limit, String offset) {
        CustomList<Card> cards = new CustomArrayList<>();
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;
        cardRepository.findAll(PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit)))
                .forEach(cards::add);
        return cards;
    }

    @Override
    public boolean delete(String id) {
        if (!validatorCard.isValidNumberCard(id)) {
            return false;
        }
        Optional<Card> card = findById(id);
        if (card.isPresent()) {
            cardRepository.delete(card.get());
            return true;
        }
        return false;

    }

}
