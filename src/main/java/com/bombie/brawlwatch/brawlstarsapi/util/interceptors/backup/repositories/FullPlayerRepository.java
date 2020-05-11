package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;

public interface FullPlayerRepository extends CrudRepository<FullPlayer, String> {

    @Override
    public <S extends FullPlayer> S save(S entity);

    @Override
    public Optional<FullPlayer> findById(String id);
}
