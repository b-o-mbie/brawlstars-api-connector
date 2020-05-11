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

public abstract class BrawlStarsAPIProxyInterceptorTemplate implements BrawlStarsAPICallBiInterceptor {

    private static final int METHOD_NAME_THREAD_STACKSTRACE_LEVEL = 3;

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return generalizeRequest(playerTag);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return generalizeRequest(playerTag);
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return generalizeRequest(clubTag);
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return generalizeRequest(clubTag);
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return generalizeRequest(country);
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return generalizeRequest(country);
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return generalizeRequest(country, brawlerId);
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return generalizeRequest();
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return generalizeRequest(brawlerId);
    }

    @Override
    public void setPlayer(FullPlayer value, String playerTag) {
        generalizeResponse(value, playerTag);
    }

    @Override
    public void setPlayerBattleLog(List<Battle> value, String playerTag) {
        generalizeResponse(value, playerTag);
    }

    @Override
    public void setClub(FullClub value, String clubTag) {
        generalizeResponse(value, clubTag);
    }

    @Override
    public void setClubMembers(List<ClubMemberPlayer> value, String clubTag) {
        generalizeResponse(value, clubTag);
    }

    @Override
    public void setPlayerRanking(List<RankingPlayer> value, String country) {
        generalizeResponse(value, country);
    }

    @Override
    public void setClubRanking(List<RankingClub> value, String country) {
        generalizeResponse(value, country);
    }

    @Override
    public void setBrawlerRanking(List<RankingPlayer> value, String country, int brawlerId) {
        generalizeResponse(value, country, brawlerId);
    }

    @Override
    public void setBrawlers(List<BrawlerReference> value) {
        generalizeResponse(value);
    }

    @Override
    public void setBrawler(BrawlerReference value, int brawlerId) {
        generalizeResponse(value, brawlerId);
    }

    private <V> V generalizeRequest(Object... args) {
        return requestHandler(getMethodName(), args);
    }

    abstract protected <V> V requestHandler(String methodName, Object... args);

    private <V> void generalizeResponse(V value, Object... args) {
        responseHandler(value, getMethodName().replaceFirst("set", "get"), args);
    }

    abstract protected <V> void responseHandler(V value, String methodName, Object... args);

    private String getMethodName() {
        return Thread.currentThread()
                .getStackTrace()[METHOD_NAME_THREAD_STACKSTRACE_LEVEL]
                        .getMethodName();
    }

}
