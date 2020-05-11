package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.BattlePlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.BattlePlayerCompositeId;

public interface BattlePlayerRepository extends CrudRepository<BattlePlayer, BattlePlayerCompositeId> {

    @Override
    <S extends BattlePlayer> S save(S entity);

    @Override
    <S extends BattlePlayer> Iterable<S> saveAll(Iterable<S> entities);

    List<BattlePlayer> findByTimestampAndPlayerListIdentifierAndTag(LocalDateTime timestamp, String playerListIdentifier, String tag);

    List<BattlePlayer> findAllByTimestampAndPlayerListIdentifier(LocalDateTime timestamp, String playerListIdentifier);

    List<BattlePlayer> findFirst25ByTagOrderByTimestampDesc(String tag);
}
