package org.metlushko.cash.config;

import org.metlushko.cash.cache.Cache;
import org.metlushko.cash.cache.impl.LFUCache;
import org.metlushko.cash.cache.impl.LRUCache;
import org.metlushko.cash.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:application.properties")
@Component
public class CasheProvider {

    @Value("${cache.type}")
    private String casheType;

    @Value("${cache.pool.size}")
    private String poolSize;

    @Bean
    public Cache<User, String> cache() {
        int capacity = Integer.parseInt(poolSize);

        return casheType.equals("lfu") ? new LRUCache(capacity) : new LFUCache(capacity);
    }
}

