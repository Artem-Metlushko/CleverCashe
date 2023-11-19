package org.metlushko.cash.cache.impl;

import org.metlushko.cash.cache.Cache;

import java.util.HashMap;
import java.util.LinkedList;


public class LRUCache<T, K> implements Cache<T, K> {

    private HashMap<K, T> data ;
    private LinkedList<K> order;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>();
        this.order = new LinkedList<>();
    }

    @Override
    public T get(K key) {
        T result = data.get(key);
        if (result != null) {
            order.remove(key);
            order.addFirst(key);
        }
        return result;
    }

    @Override
    public void put(K key, T value) {
        if (order.size() >= capacity) {
            K keyRemoved = order.removeLast();
            data.remove(keyRemoved);
        }
        order.addFirst(key);
        data.put(key, value);
    }

    @Override
    public void remove(K key) {
        order.remove(key);
        data.remove(key);
    }

    @Override
    public T update(K key, T value) {
        T oldValue = data.put(key, value);
        order.remove(key);
        order.addFirst(key);
        return oldValue;
    }


}
