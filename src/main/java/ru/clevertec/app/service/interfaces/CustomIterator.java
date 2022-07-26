package ru.clevertec.app.service.interfaces;

import java.util.Iterator;

public interface CustomIterator<E> extends Iterator<E> {

    E next();

    boolean hasNext();

    void remove();

    void addBefore(E e);

    void addAfter(E e);

}
