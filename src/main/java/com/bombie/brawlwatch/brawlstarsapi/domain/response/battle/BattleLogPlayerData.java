package com.bombie.brawlwatch.brawlstarsapi.domain.response.battle;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.PlayerReference;

import lombok.Data;

@Data
public class BattleLogPlayerData implements PlayerReference {
    private String tag;
    private String name;
    private BattleLogBrawlerData brawler;

}
