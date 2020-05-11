package com.bombie.brawlwatch.brawlstarsapi.util.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;

@Service
public class BrawlStarsAPISpringRestTemplateAdapter {
    @Autowired
    private BrawlStarsAPIResponseHeaderDecoder exceptionDecoder;
    @Autowired
    private HttpEntity<String> requestEntity;

    private RestTemplate restTemplate;

    @Autowired
    private void restTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public <T> T request(String url, ParameterizedTypeReference<T> responseType, Object... args) {
        T responseBody = null;
        try {
            responseBody = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, args).getBody();
        } catch (RestClientException e) {
            throw new BrawlStarsAPIException(exceptionDecoder.decodeMessage(e.getMessage()));
        }
        return responseBody;
    }
}
