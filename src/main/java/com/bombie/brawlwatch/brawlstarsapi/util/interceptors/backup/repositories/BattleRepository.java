package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.BattleCompositeId;

public interface BattleRepository extends CrudRepository<Battle, BattleCompositeId> {

    @Override
    <S extends Battle> S save(S entity);

    @Override
    <S extends Battle> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Battle> findByTimestampAndPlayerListIdentifier(LocalDateTime timestamp, String playerListIdentifier);

}
