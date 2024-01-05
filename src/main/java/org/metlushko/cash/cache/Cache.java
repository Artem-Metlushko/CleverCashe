package org.metlushko.cash.cache;

public interface Cache<T,K> {

    T get(K key);

    void put(K key, T value);

    void remove( K key);

    T update(K key,T value);
}
