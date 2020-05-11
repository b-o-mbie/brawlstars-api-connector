package com.bombie.brawlwatch.brawlstarsapi.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource("classpath:config/application.properties")
@ConfigurationProperties(prefix = "bsapi.cache.lifespan")
public class BrawlStarsAPICacheSpringConfiguration {
    private boolean isDefaultEnabled;
    private int defaultCacheLifespan;

    private int player;
    private int playerBattleLog;
    private int club;
    private int clubMembers;
    private int playerRanking;
    private int clubRanking;
    private int brawlerRanking;
    private int brawlers;
    private int brawler;
}
