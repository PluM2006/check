package ru.clevertec.app.repository;

import ru.clevertec.app.customlist.CustomList;

import java.util.Optional;

public interface Repository<T> {
    T add(T t);

    T update(T t);

    Optional<T> findById(Long id);

    CustomList<T> findAll();

    boolean delete(Long id);

}
