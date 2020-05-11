package com.bombie.brawlwatch.brawlstarsapi.util;

import java.util.List;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

public interface BrawlStarsAPIProxy {

    FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException;

    List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException;

    FullClub getClub(String clubTag) throws BrawlStarsAPIException;

    List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException;

    List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException;

    List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException;

    List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException;

    List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException;

    BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException;

}
