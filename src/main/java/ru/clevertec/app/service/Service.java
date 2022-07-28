package ru.clevertec.app.service;

import ru.clevertec.app.customlist.CustomList;

import java.util.Map;
import java.util.Optional;

public interface Service<T> {
    T add(Map<String, String> parameters);

    T update(Map<String, String> parameters);

    Optional<T> findById(Long id);

    CustomList<T> findAll();

    boolean delete(Long id);

}
