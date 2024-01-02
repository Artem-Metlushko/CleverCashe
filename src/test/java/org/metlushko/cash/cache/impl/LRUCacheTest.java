package org.metlushko.cash.cache.impl;

import org.junit.jupiter.api.Test;
import org.metlushko.cash.cache.impl.LRUCache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LRUCacheTest {

    private LRUCache<String, Integer> cache =new LRUCache<>(3);;

    @Test
    void testPutAndGet() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        assertEquals("One", cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals("Three", cache.get(3));
    }

    @Test
    void testEviction() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.put(4, "Four");  // Evicts 1 (LRU)

        assertNull(cache.get(1));  // Entry with key 1 should be evicted
        assertEquals("Two", cache.get(2));
        assertEquals("Three", cache.get(3));
        assertEquals("Four", cache.get(4));
    }

    @Test
    void testRemove() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        cache.remove(2);  // Remove entry with key 2

        assertNull(cache.get(2));  // Entry with key 2 should be removed
        assertEquals("One", cache.get(1));
        assertEquals("Three", cache.get(3));
    }

    @Test
    void testUpdate() {
        cache.put(1, "One");
        cache.put(2, "Two");

        assertEquals("One", cache.update(1, "NewOne"));  // Update value for key 1

        assertEquals("NewOne", cache.get(1));
        assertEquals("Two", cache.get(2));
    }
}
