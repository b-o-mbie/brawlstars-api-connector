package com.bombie.brawlwatch.brawlstarsapi.domain.response.battle;

import java.util.List;

import lombok.Data;

@Data
public class BattleData {
    private String mode;
    private String type;

    private int duration;

    private String result;
    private int rank;

    private BattleLogPlayerData starPlayer;

    private List<List<BattleLogPlayerData>> teams;
    private List<BattleLogPlayerData> players;

    private int trophyChange;
    private BossFightLevel level;
}
