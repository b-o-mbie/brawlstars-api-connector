package com.bombie.brawlwatch.brawlstarsapi.domain.response.cache;

public class NoSuchCacheConfiguredException extends RuntimeException {
    private static final long serialVersionUID = 7892363360663171396L;

    public NoSuchCacheConfiguredException() {
        super();
    }

    public NoSuchCacheConfiguredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCacheConfiguredException(String message) {
        super(message);
    }

    public NoSuchCacheConfiguredException(Throwable cause) {
        super(cause);
    }

}
