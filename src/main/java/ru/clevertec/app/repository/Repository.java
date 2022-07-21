package ru.clevertec.app.repository;

import ru.clevertec.app.service.CustomList;

import java.util.Optional;

public interface Repository<T> {
    T add(T t);

    T update(T t, Long id);

    Optional<T> findById(Long id);

    CustomList<T> findAll();

    boolean delete(Long id);

}
