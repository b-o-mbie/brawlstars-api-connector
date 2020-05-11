package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.caching;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPIProxyInterceptorTemplate;

@Service
public class BrawlStarsAPIMapCacheInterceptor extends BrawlStarsAPIProxyInterceptorTemplate {

    @Autowired
    private CacheFactory cacheFactory;

    @Override
    protected <V> V requestHandler(String methodName, Object... args) {
        String key = combineArguments(args);

        CacheMap<String, V> cache = cacheFactory.getCacheOfMethod(methodName);
        return getValueOrNull(cache, key);
    }

    private <V> V getValueOrNull(CacheMap<String, V> cache, String key) {
        V cachedValue = null;
        if (cache.containsKey(key)) {
            cachedValue = cache.get(key);
        }
        return cachedValue;
    }

    @Override
    protected <V> void responseHandler(V value, String methodName, Object... args) {
        String combinedArgs = combineArguments(args);

        CacheMap<String, V> cache = cacheFactory.getCacheOfMethod(methodName);
        cache.put(combinedArgs, value);
    }

    private String combineArguments(Object... args) {
        StringBuilder combiner = new StringBuilder(".");
        Arrays.stream(args).forEach(obj -> combiner.append(obj.toString()));
        return combiner.toString();
    }
}
