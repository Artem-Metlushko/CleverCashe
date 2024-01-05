/*
package org.metlushko.cash.util;

import lombok.experimental.UtilityClass;
import org.metlushko.cash.cache.Cache;
import org.metlushko.cash.cache.impl.LFUCache;
import org.metlushko.cash.cache.impl.LRUCache;

@UtilityClass
public class CacheManager {

    private static final String CASHE_TYPE = "cache.type";
    private static final String POOL_SIZE = "cache.pool.size";

    public static Cache getCacheType() {
        String cacheType = PropertiesUtil.get(CASHE_TYPE);
        Integer poolSize = Integer.parseInt(PropertiesUtil.get(POOL_SIZE));

        LRUCache lruCache = new LRUCache(poolSize);
        LFUCache lfuCache = new LFUCache(poolSize);

        return cacheType.equals("lfu") ? lruCache : lfuCache;

    }

}
*/
