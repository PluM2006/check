package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.CardDTO;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.mapper.CardMapper;
import ru.clevertec.app.repository.CardRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidatorCard;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class CardCheckService implements CheckService<CardDTO, Card> {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final ValidatorCard validatorCard;

    @Override
    public CardDTO add(Card card) {
        if (!validatorCard.isValidParametersCard(card)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cardMapper.toCardDTO(cardRepository.save(card));
    }

    @Override
    public CardDTO update(Card card) {
        if (!validatorCard.isValidParametersCard(card)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cardMapper.toCardDTO(cardRepository.save(card));
    }

    @Override
    public CardDTO findById(String id) {
        if (!validatorCard.isValidIdCard(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cardRepository.findById(Long.parseLong(id))
                .map(cardMapper::toCardDTO)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public CustomList<CardDTO> findAll() {
        CustomList<CardDTO> cards = new CustomArrayList<>();
        cardRepository.findAll().stream().map(cardMapper::toCardDTO).forEach(cards::add);
        return cards;
    }

    @Override
    public CustomList<CardDTO> findAll(Pageable pageable) {
        CustomList<CardDTO> cards = new CustomArrayList<>();
        cardRepository.findAll(pageable)
                .map(cardMapper::toCardDTO)
                .forEach(cards::add);
        return cards;
    }

    @Override
    public boolean delete(String id) {
        if (!validatorCard.isValidIdCard(id)) {
            return false;
        }
        Optional<Card> card = cardRepository.findById(Long.parseLong(id));
        if (card.isPresent()) {
            cardRepository.delete(card.get());
            return true;
        }
        return false;

    }

}
