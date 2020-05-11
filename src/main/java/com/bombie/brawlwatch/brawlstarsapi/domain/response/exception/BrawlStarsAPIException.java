package com.bombie.brawlwatch.brawlstarsapi.domain.response.exception;

public class BrawlStarsAPIException extends RuntimeException {
    private static final long serialVersionUID = 5007694614398177652L;

    public BrawlStarsAPIException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public BrawlStarsAPIException(BrawlStarsAPIResponseHeader reason, Throwable cause) {
        super(reason.toString(), cause);
    }

    public BrawlStarsAPIException(BrawlStarsAPIResponseHeader reason) {
        super(reason.toString());
    }

    public BrawlStarsAPIException(Throwable cause) {
        super(cause);
    }

    public BrawlStarsAPIException() {
        super();
    }
}
