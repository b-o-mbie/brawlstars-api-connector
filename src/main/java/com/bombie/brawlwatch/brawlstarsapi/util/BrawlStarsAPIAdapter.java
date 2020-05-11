package com.bombie.brawlwatch.brawlstarsapi.util;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

@Service
public class BrawlStarsAPIAdapter implements OptionalBrawlStarsAPI, BrawlStarsAPIProxy {

    private BrawlStarsAPIProxy parentAPI;

    public BrawlStarsAPIAdapter(BrawlStarsAPIProxy parentAPI) {
        this.parentAPI = parentAPI;
    }

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return parentAPI.getPlayer(playerTag);
    }

    @Override
    public Optional<FullPlayer> findPlayer(String playerTag) {
        return wrapOneArgMethod(this::getPlayer, playerTag);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return parentAPI.getPlayerBattleLog(playerTag);
    }

    @Override
    public Optional<List<Battle>> findPlayerBattleLog(String playerTag) {
        return wrapOneArgMethod(this::getPlayerBattleLog, playerTag);
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return parentAPI.getClub(clubTag);
    }

    @Override
    public Optional<FullClub> findClub(String clubTag) {
        return wrapOneArgMethod(this::getClub, clubTag);
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return parentAPI.getClubMembers(clubTag);
    }

    @Override
    public Optional<List<ClubMemberPlayer>> findClubMembers(String clubTag) {
        return wrapOneArgMethod(this::getClubMembers, clubTag);
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return parentAPI.getPlayerRanking(country);
    }

    @Override
    public Optional<List<RankingPlayer>> findPlayerRanking(String country) {
        return wrapOneArgMethod(this::getPlayerRanking, country);
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return parentAPI.getClubRanking(country);
    }

    @Override
    public Optional<List<RankingClub>> findClubRanking(String country) {
        return wrapOneArgMethod(this::getClubRanking, country);
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return parentAPI.getBrawlerRanking(country, brawlerId);
    }

    @Override
    public Optional<List<RankingPlayer>> findBrawlerRanking(String country, int brawlerId) {
        return wrapTwoArgMethod(this::getBrawlerRanking, country, brawlerId);
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return parentAPI.getBrawlers();
    }

    @Override
    public Optional<List<BrawlerReference>> findBrawlers() {
        return wrapZeroArgMethod(this::getBrawlers);
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return parentAPI.getBrawler(brawlerId);
    }

    @Override
    public Optional<BrawlerReference> findBrawler(int brawlerId) {
        return wrapOneArgMethod(this::getBrawler, brawlerId);
    }

    private <R> Optional<R> wrapZeroArgMethod(Supplier<R> method) {
        Optional<R> optional;
        try {
            optional = Optional.of(method.get());
        } catch (BrawlStarsAPIException e) {
            optional = Optional.empty();
        }
        return optional;
    }

    private <T, R> Optional<R> wrapOneArgMethod(Function<T, R> method, T arg) {
        return wrapZeroArgMethod(() -> method.apply(arg));
    }

    private <T, U, R> Optional<R> wrapTwoArgMethod(BiFunction<T, U, R> method, T arg1, U arg2) {
        return wrapZeroArgMethod(() -> method.apply(arg1, arg2));
    }

}
