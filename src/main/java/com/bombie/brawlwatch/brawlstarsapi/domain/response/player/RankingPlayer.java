package com.bombie.brawlwatch.brawlstarsapi.domain.response.player;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.PlayerRankingClub;

import lombok.Data;

@Data
public class RankingPlayer implements PlayerReference {
    private String tag;
    private String name;
    private String nameColor;
    private int trophies;
    private int rank;
    private PlayerRankingClub club;
}
