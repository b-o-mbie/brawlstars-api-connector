package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.stub;

import java.util.List;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallConsumerInterceptor;

public class BrawlStarsAPICallConsumerInterceptorStub implements BrawlStarsAPICallConsumerInterceptor {

    @Override
    public void setPlayer(FullPlayer value, String playerTag) {
    }

    @Override
    public void setPlayerBattleLog(List<Battle> value, String playerTag) {
    }

    @Override
    public void setClub(FullClub value, String clubTag) {
    }

    @Override
    public void setClubMembers(List<ClubMemberPlayer> value, String clubTag) {
    }

    @Override
    public void setPlayerRanking(List<RankingPlayer> value, String country) {
    }

    @Override
    public void setClubRanking(List<RankingClub> value, String country) {
    }

    @Override
    public void setBrawlerRanking(List<RankingPlayer> value, String country, int brawlerId) {
    }

    @Override
    public void setBrawlers(List<BrawlerReference> value) {
    }

    @Override
    public void setBrawler(BrawlerReference value, int brawlerId) {
    }

}
