package com.bombie.brawlwatch.brawlstarsapi.spring;

import java.util.Collections;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource("classpath:config/application.properties")
@ConfigurationProperties(prefix = "bsapi")
public class BrawlStarsAPISpringConfiguration {

    private String apikey;
    private String baseurl;

    @Bean
    public HttpEntity<String> getRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("authorization", "Bearer " + apikey);
        return new HttpEntity<String>("body", headers);
    }
}
