package com.bombie.brawlwatch.brawlstarsapi.util.proxy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;
import com.bombie.brawlwatch.brawlstarsapi.spring.BrawlStarsAPIUrlSpringConfiguration;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIProxy;

@Service
public class BrawlStarsAPIProxyImpl implements BrawlStarsAPIProxy {

    @Autowired
    private BrawlStarsAPIUrlSpringConfiguration urls;

    @Autowired
    private BrawlStarsAPISpringRestTemplateAdapter restAPI;

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        Object[] args = {playerTag};
        return restAPI.request(urls.getGetPlayerUrl(), BrawlStarsAPIResponseTypeReferences.FULL_PLAYER_REF, args);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        Object[] args = {playerTag};
        return restAPI.request(urls.getGetPlayerBattleLogUrl(), BrawlStarsAPIResponseTypeReferences.BATTLE_LOG_LIST_RESPONSE_REF, args)
                .getItems().stream()
                .filter(battle -> "ranked".equals(battle.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        Object[] args = {clubTag};
        return restAPI.request(urls.getGetClubUrl(), BrawlStarsAPIResponseTypeReferences.FULL_CLUB_REF, args);
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        Object[] args = {clubTag};
        return restAPI
                .request(urls.getGetClubMembersUrl(), BrawlStarsAPIResponseTypeReferences.CLUB_MEMBER_PLAYER_LIST_RESPONSE_REF, args)
                .getItems();
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        Object[] args = {country};
        return restAPI.request(urls.getGetPlayerRankingUrl(), BrawlStarsAPIResponseTypeReferences.RANKING_PLAYER_LIST_RESPONSE_REF, args)
                .getItems();
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        Object[] args = {country};
        return restAPI.request(urls.getGetClubRankingUrl(), BrawlStarsAPIResponseTypeReferences.RANKING_CLUB_LIST_RESPONSE_REF, args)
                .getItems();
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        Object[] args = {country, brawlerId};
        return restAPI.request(urls.getGetBrawlerRankingUrl(), BrawlStarsAPIResponseTypeReferences.RANKING_PLAYER_LIST_RESPONSE_REF, args)
                .getItems();
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return restAPI.request(urls.getGetBrawlersUrl(), BrawlStarsAPIResponseTypeReferences.GENERIC_BRAWLER_LIST_RESPONSE_REF)
                .getItems();
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        Object[] args = {brawlerId};
        return restAPI.request(urls.getGetBrawlerUrl(), BrawlStarsAPIResponseTypeReferences.GENERIC_BRAWLER_REF, args);
    }
}
