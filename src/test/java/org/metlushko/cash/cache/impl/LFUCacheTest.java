package org.metlushko.cash.cache.impl;

import org.junit.jupiter.api.Test;
import org.metlushko.cash.cache.impl.LFUCache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class LFUCacheTest {

    private LFUCache<Integer, String> lfuCache = new LFUCache<>(3);


    @Test
    void testCachePutAndGet() {

        //when
        lfuCache.put("one", 1);
        lfuCache.put("two", 2);
        lfuCache.put("three", 3);

        //then
        assertEquals(1, lfuCache.get("one"));
        assertEquals(2, lfuCache.get("two"));
        assertEquals(3, lfuCache.get("three"));

    }

    @Test
    void testCacheEviction() {

        //given
        lfuCache.put("one", 1);
        lfuCache.put("two", 2);
        lfuCache.put("three", 3);

        //when
        lfuCache.get("one");
        lfuCache.get("two");
        lfuCache.put("four", 4);

        //then
        assertNull(lfuCache.get("three"));// Evicted
        assertEquals(1, lfuCache.get("one"));
        assertEquals(2, lfuCache.get("two"));
        assertEquals(4, lfuCache.get("four"));
    }

    @Test
    void testCacheRemove() {

        //given
        lfuCache.put("one", 1);
        lfuCache.put("two", 2);

        //when
        lfuCache.remove("one");

        //then
        assertNull(lfuCache.get("one"));
        assertEquals(2, lfuCache.get("two"));
    }

    @Test
    void testCacheUpdate() {
        //given
        lfuCache.put("one", 1);

        //when
        lfuCache.update("one", 11);

        //then
        assertEquals(11, lfuCache.get("one"));
    }

    @Test
    void testNonexistentKeyUpdate() {
        assertNull(lfuCache.update("nonexistent", 42));
    }
}
