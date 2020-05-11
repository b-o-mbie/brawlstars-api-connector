package com.bombie.brawlwatch.brawlstarsapi.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource("classpath:config/application.properties")
@ConfigurationProperties(prefix = "bsapi.url")
public class BrawlStarsAPIUrlSpringConfiguration {
    private String getBrawlerUrl;
    private String getBrawlersUrl;
    private String getBrawlerRankingUrl;
    private String getClubRankingUrl;
    private String getPlayerRankingUrl;
    private String getClubMembersUrl;
    private String getPlayerBattleLogUrl;
    private String getPlayerUrl;
    private String getClubUrl;
}
