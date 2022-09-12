package ru.clevertec.app.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.app.customlist.CustomList;

public interface CheckService<T, S> {
    T add(S s);

    T update(S s);

    T findById(String id);

    CustomList<T> findAll();

    CustomList<T> findAll(Pageable pageable);

    boolean delete(String id);

}
