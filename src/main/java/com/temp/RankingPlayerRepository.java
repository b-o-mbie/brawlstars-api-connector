package com.temp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;

public interface RankingPlayerRepository extends CrudRepository<RankingPlayer, String> {

    @Override
    <S extends RankingPlayer> S save(S entity);

    @Override
    <S extends RankingPlayer> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<RankingPlayer> findById(String id);

    @Override
    List<RankingPlayer> findAll();

}
