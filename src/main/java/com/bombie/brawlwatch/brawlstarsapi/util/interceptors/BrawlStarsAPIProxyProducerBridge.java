package com.bombie.brawlwatch.brawlstarsapi.util.interceptors;

import java.util.List;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIProxy;

public class BrawlStarsAPIProxyProducerBridge implements BrawlStarsAPICallProducerInterceptor, BrawlStarsAPIProxy {

    private BrawlStarsAPIProxy proxy;

    public BrawlStarsAPIProxyProducerBridge(BrawlStarsAPIProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return proxy.getPlayer(playerTag);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return proxy.getPlayerBattleLog(playerTag);
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return proxy.getClub(clubTag);
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return proxy.getClubMembers(clubTag);
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return proxy.getPlayerRanking(country);
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return proxy.getClubRanking(country);
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return proxy.getBrawlerRanking(country, brawlerId);
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return proxy.getBrawlers();
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return proxy.getBrawler(brawlerId);
    }

}
