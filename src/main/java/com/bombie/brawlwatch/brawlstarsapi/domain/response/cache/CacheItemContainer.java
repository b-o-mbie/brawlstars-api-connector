package com.bombie.brawlwatch.brawlstarsapi.domain.response.cache;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheItemContainer<T> {
    private T value;
    private final LocalDateTime timestamp;
}
