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
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.stub.BrawlStarsAPICallConsumerInterceptorStub;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.stub.BrawlStarsAPICallProducerInterceptorStub;

public class BrawlStarsAPICallBiInterceptorWrapper
    implements BrawlStarsAPICallBiInterceptor, BrawlStarsAPICallConsumerInterceptor, BrawlStarsAPICallProducerInterceptor {

    private static BrawlStarsAPICallProducerInterceptor producerStub = new BrawlStarsAPICallProducerInterceptorStub();

    private static BrawlStarsAPICallConsumerInterceptor consumerStub = new BrawlStarsAPICallConsumerInterceptorStub();

    private BrawlStarsAPICallProducerInterceptor wrappedProducer;
    private BrawlStarsAPICallConsumerInterceptor wrappedConsumer;

    public BrawlStarsAPICallBiInterceptorWrapper(BrawlStarsAPICallProducerInterceptor wrappedProducer,
            BrawlStarsAPICallConsumerInterceptor wrappedConsumer) {
        this.wrappedProducer = wrappedProducer;
        this.wrappedConsumer = wrappedConsumer;
    }

    public BrawlStarsAPICallBiInterceptorWrapper(BrawlStarsAPICallProducerInterceptor wrappedProducer) {
        this(wrappedProducer, consumerStub);
    }

    public BrawlStarsAPICallBiInterceptorWrapper(BrawlStarsAPICallConsumerInterceptor wrappedConsumer) {
        this(producerStub, wrappedConsumer);
    }

    public BrawlStarsAPICallBiInterceptorWrapper() {
        this(producerStub, consumerStub);
    }

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return wrappedProducer.getPlayer(playerTag);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return wrappedProducer.getPlayerBattleLog(playerTag);
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return wrappedProducer.getClub(clubTag);
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return wrappedProducer.getClubMembers(clubTag);
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return wrappedProducer.getPlayerRanking(country);
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return wrappedProducer.getClubRanking(country);
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return wrappedProducer.getBrawlerRanking(country, brawlerId);
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return wrappedProducer.getBrawlers();
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return wrappedProducer.getBrawler(brawlerId);
    }

    @Override
    public void setPlayer(FullPlayer value, String playerTag) {
        wrappedConsumer.setPlayer(value, playerTag);
    }

    @Override
    public void setPlayerBattleLog(List<Battle> value, String playerTag) {
        wrappedConsumer.setPlayerBattleLog(value, playerTag);
    }

    @Override
    public void setClub(FullClub value, String clubTag) {
        wrappedConsumer.setClub(value, clubTag);
    }

    @Override
    public void setClubMembers(List<ClubMemberPlayer> value, String clubTag) {
        wrappedConsumer.setClubMembers(value, clubTag);
    }

    @Override
    public void setPlayerRanking(List<RankingPlayer> value, String country) {
        wrappedConsumer.setPlayerRanking(value, country);
    }

    @Override
    public void setClubRanking(List<RankingClub> value, String country) {
        wrappedConsumer.setClubRanking(value, country);
    }

    @Override
    public void setBrawlerRanking(List<RankingPlayer> value, String country, int brawlerId) {
        wrappedConsumer.setBrawlerRanking(value, country, brawlerId);
    }

    @Override
    public void setBrawlers(List<BrawlerReference> value) {
        wrappedConsumer.setBrawlers(value);
    }

    @Override
    public void setBrawler(BrawlerReference value, int brawlerId) {
        wrappedConsumer.setBrawler(value, brawlerId);
    }

}
