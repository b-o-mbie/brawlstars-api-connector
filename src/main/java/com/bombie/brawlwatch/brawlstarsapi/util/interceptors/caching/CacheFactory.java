package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.caching;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.cache.NoSuchCacheConfiguredException;
import com.bombie.brawlwatch.brawlstarsapi.spring.BrawlStarsAPICacheSpringConfiguration;

@Service
public class CacheFactory {
    @Autowired
    BrawlStarsAPICacheSpringConfiguration config;

    Map<String, CacheMap<Object, Object>> cacheContainer = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <K, V> CacheMap<K, V> getCacheOfMethod(String methodName) {
        CacheMap<Object, Object> cache = null;
        if (cacheContainer.containsKey(methodName)) {
            cache = cacheContainer.get(methodName);
        } else {
            cacheContainer.putIfAbsent(methodName, new CacheMap<>(getLifespanOfMethodCache(methodName)));
            cache = getCacheOfMethod(methodName);
        }
        return (CacheMap<K, V>) cache;
    }

    private Duration getLifespanOfMethodCache(String methodName) {
        int lifespanInMinutes = config.getDefaultCacheLifespan();
        try {
            lifespanInMinutes = (int) config.getClass().getMethod(methodName).invoke(config);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            if (!config.isDefaultEnabled()) {
                throw new NoSuchCacheConfiguredException("missing cache for method: " + methodName);
            }
        }
        return Duration.ofMinutes(lifespanInMinutes);
    }
}
