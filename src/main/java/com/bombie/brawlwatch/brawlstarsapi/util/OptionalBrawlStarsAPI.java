package com.bombie.brawlwatch.brawlstarsapi.util;

import java.util.List;
import java.util.Optional;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

public interface OptionalBrawlStarsAPI {

    Optional<FullPlayer> findPlayer(String playerTag);

    Optional<List<Battle>> findPlayerBattleLog(String playerTag);

    Optional<FullClub> findClub(String clubTag);

    Optional<List<ClubMemberPlayer>> findClubMembers(String clubTag);

    Optional<List<RankingPlayer>> findPlayerRanking(String country);

    Optional<List<RankingClub>> findClubRanking(String country);

    Optional<List<RankingPlayer>> findBrawlerRanking(String country, int brawlerId);

    Optional<List<BrawlerReference>> findBrawlers();

    Optional<BrawlerReference> findBrawler(int brawlerId);

}
