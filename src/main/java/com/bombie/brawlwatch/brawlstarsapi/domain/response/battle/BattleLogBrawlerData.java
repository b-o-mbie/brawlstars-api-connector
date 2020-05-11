package com.bombie.brawlwatch.brawlstarsapi.domain.response.battle;

import javax.persistence.Embeddable;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.GenericBrawler;

import lombok.Data;

@Data
@Embeddable
public class BattleLogBrawlerData implements GenericBrawler {
    private int id;
    private String name;
    private int power;
    private int trophies;
}
