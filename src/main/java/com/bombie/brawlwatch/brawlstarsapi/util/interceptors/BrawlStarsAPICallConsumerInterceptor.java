package com.bombie.brawlwatch.brawlstarsapi.util.interceptors;

import java.util.List;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

public interface BrawlStarsAPICallConsumerInterceptor {
    void setPlayer(FullPlayer value, String playerTag);

    void setPlayerBattleLog(List<Battle> value, String playerTag);

    void setClub(FullClub value, String clubTag);

    void setClubMembers(List<ClubMemberPlayer> value, String clubTag);

    void setPlayerRanking(List<RankingPlayer> value, String country);

    void setClubRanking(List<RankingClub> value, String country);

    void setBrawlerRanking(List<RankingPlayer> value, String country, int brawlerId);

    void setBrawlers(List<BrawlerReference> value);

    void setBrawler(BrawlerReference value, int brawlerId);

}
