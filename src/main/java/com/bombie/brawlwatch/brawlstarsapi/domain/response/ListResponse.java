package com.bombie.brawlwatch.brawlstarsapi.domain.response;

import java.util.List;

import lombok.Data;

@Data
public class ListResponse<T> {
    private List<T> items;
}
