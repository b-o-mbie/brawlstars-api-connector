package com.bombie.brawlwatch.brawlstarsapi.domain.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrawlStarsAPIResponseHeader {
    private String reason;
    private String message;
    private int code;
    private String type;
}
