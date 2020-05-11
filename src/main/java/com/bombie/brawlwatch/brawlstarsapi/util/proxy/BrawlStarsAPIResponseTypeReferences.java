package com.bombie.brawlwatch.brawlstarsapi.util.proxy;

import org.springframework.core.ParameterizedTypeReference;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.ListResponse;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

final class BrawlStarsAPIResponseTypeReferences {
    public static final ParameterizedTypeReference<FullPlayer> FULL_PLAYER_REF = new ParameterizedTypeReference<FullPlayer>() {
    };
    public static final ParameterizedTypeReference<FullClub> FULL_CLUB_REF = new ParameterizedTypeReference<FullClub>() {
    };
    public static final ParameterizedTypeReference<BrawlerReference> GENERIC_BRAWLER_REF = new ParameterizedTypeReference<BrawlerReference>() {
    };
    public static final ParameterizedTypeReference<ListResponse<Battle>> BATTLE_LOG_LIST_RESPONSE_REF = new ParameterizedTypeReference<ListResponse<Battle>>() {
    };
    public static final ParameterizedTypeReference<ListResponse<ClubMemberPlayer>> CLUB_MEMBER_PLAYER_LIST_RESPONSE_REF = new ParameterizedTypeReference<ListResponse<ClubMemberPlayer>>() {
    };
    public static final ParameterizedTypeReference<ListResponse<RankingClub>> RANKING_CLUB_LIST_RESPONSE_REF = new ParameterizedTypeReference<ListResponse<RankingClub>>() {
    };
    public static final ParameterizedTypeReference<ListResponse<RankingPlayer>> RANKING_PLAYER_LIST_RESPONSE_REF = new ParameterizedTypeReference<ListResponse<RankingPlayer>>() {
    };
    public static final ParameterizedTypeReference<ListResponse<BrawlerReference>> GENERIC_BRAWLER_LIST_RESPONSE_REF = new ParameterizedTypeReference<ListResponse<BrawlerReference>>() {
    };
}
