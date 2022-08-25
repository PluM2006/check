package ru.clevertec.app.service;

import ru.clevertec.app.customlist.CustomList;

import java.util.Optional;

public interface CheckService<T> {
    Optional<T> add(T t);

    Optional<T> update(T t);

    Optional<T> findById(String id);

    CustomList<T> findAll();

    CustomList<T> findAll(String limit, String offset);

    boolean delete(String id);

}
