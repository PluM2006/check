package ru.clevertec.app.customlist;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface CustomList<E> extends Iterable<E> {

    CustomIterator<E> getIterator();

    void setMaxSize(int maxSize);

    void add(E e);

    void addAll(CustomList<? extends E> c);

    E set(int index, E e);

    E remove(int index);

    void clear();

    int find(E e);

    E get(int index);

    Object[] toArray();

    int size();

    void trim();

    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
