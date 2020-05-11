package com.bombie.brawlwatch.brawlstarsapi.spring;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIAdapter;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallBiInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallBiInterceptorWrapper;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallConsumerInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallProducerInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPIProxyProducerBridge;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.InterceptableBrawlStarsAPI;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.BrawlStarsAPIDatabaseInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.caching.BrawlStarsAPIMapCacheInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.proxy.BrawlStarsAPIProxyImpl;

@Configuration
public class BasicBrawlStarsAPISpringBeanConfiguration {

    @Bean
    @Primary
    public BrawlStarsAPIAdapter getFullFeaturedAPIAdapter(InterceptableBrawlStarsAPI apiContainer) {
        apiContainer.addInterceptors(getAPISourceChain());
        apiContainer.addObservers(getBrawlStarsAPIExceptionObservers());
        return new BrawlStarsAPIAdapter(apiContainer);
    }

    @Autowired
    private BrawlStarsAPIProxyImpl proxy;

    @Autowired
    private BrawlStarsAPIMapCacheInterceptor firstCache;

    @Autowired
    private BrawlStarsAPIDatabaseInterceptor database;

    private List<BrawlStarsAPICallBiInterceptor> getAPISourceChain() {
        return Arrays.asList(
                firstCache,
                new BrawlStarsAPICallBiInterceptorWrapper(getPrimaryAPISource()),
                new BrawlStarsAPICallBiInterceptorWrapper((BrawlStarsAPICallProducerInterceptor) database));
    }

    private BrawlStarsAPICallProducerInterceptor getPrimaryAPISource() {
        InterceptableBrawlStarsAPI primarySource = new InterceptableBrawlStarsAPI();
        primarySource.addInterceptors(getPrimaryAPISourceChain());
        return new BrawlStarsAPIProxyProducerBridge(primarySource);
    }

    private List<BrawlStarsAPICallBiInterceptor> getPrimaryAPISourceChain() {
        return Arrays.asList(
                getDatabaseCacheStub(),
                new BrawlStarsAPICallBiInterceptorWrapper((BrawlStarsAPICallConsumerInterceptor) database),
                new BrawlStarsAPICallBiInterceptorWrapper(new BrawlStarsAPIProxyProducerBridge(proxy)));
    }

    private BrawlStarsAPICallBiInterceptor getDatabaseCacheStub() {
        return new BrawlStarsAPICallBiInterceptorWrapper();
    }

    private List<Consumer<BrawlStarsAPIException>> getBrawlStarsAPIExceptionObservers() {
        return Arrays.asList(generateObserver(" obs1: "), generateObserver(" obs2: "));

    }

    private Consumer<BrawlStarsAPIException> generateObserver(String msg) {
        return (BrawlStarsAPIException e) -> {
            System.out.println(msg + e.getMessage());
        };
    }

}
