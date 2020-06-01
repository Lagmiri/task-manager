package com.learnjava.dao;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    T add(T data);

    Collection<T> readAll();

    Optional<T> readOne(Long id);

    T update(T data);

    T remove(T data);
}