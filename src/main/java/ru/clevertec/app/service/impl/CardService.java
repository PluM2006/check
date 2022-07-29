package ru.clevertec.app.service.impl;

import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.Service;

import java.util.Map;
import java.util.Optional;

public class CardService implements Service<Card> {
    @Override
    public Optional<Card> add(Map<String, String> parameters) {
        return Optional.empty();
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
