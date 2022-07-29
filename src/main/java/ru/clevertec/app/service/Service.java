package ru.clevertec.app.service;

import ru.clevertec.app.customlist.CustomList;

import java.util.Map;
import java.util.Optional;

public interface Service<T> {
    Optional<T> add(Map<String, String> parameters);

    Optional<T> update(Map<String, String> parameters);

    Optional<T> findById(String id);

    CustomList<T> findAll(String limit, String offset);

    boolean delete(String id);

}
