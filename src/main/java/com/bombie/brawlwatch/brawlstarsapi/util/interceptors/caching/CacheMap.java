package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.caching;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.cache.CacheItemContainer;

public class CacheMap<K, V> {
    private Map<K, CacheItemContainer<V>> cache = new ConcurrentHashMap<>();
    private Duration lifespan;

    public CacheMap(Duration lifespan) {
        this.lifespan = lifespan;
    }

    public void clear() {
        cache.clear();
    }

    /**
     * @param key
     * @return false -> not exists, true -> may exists.
     */
    public boolean containsKey(Object key) {
        return cache.containsKey(key);
    }

    public V get(K key) {
        CacheItemContainer<V> cacheItem = cache.get(key);
        V value = null;
        if (isValid(cacheItem)) {
            value = cacheItem.getValue();
        }
        return value;
    }

    private boolean isValid(CacheItemContainer<V> cacheItem) {
        return LocalDateTime.now().isBefore(cacheItem.getTimestamp().plus(lifespan)) || lifespan.isNegative();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public V put(K key, V value) {
        V previousValue;
        try {
            previousValue = cache.put(key, new CacheItemContainer<V>(value, LocalDateTime.now())).getValue();
        } catch (NullPointerException e) {
            previousValue = null;
        }
        return previousValue;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    public V remove(Object key) {
        V value;
        try {
            value = cache.remove(key).getValue();
        } catch (NullPointerException e) {
            value = null;
        }
        return value;
    }

    public int size() {
        return cache.size();
    }

    public Collection<V> values() {
        return cache.values().parallelStream()
                .filter(this::isValid)
                .map(item -> item.getValue())
                .collect(Collectors.toList());
    }

}
