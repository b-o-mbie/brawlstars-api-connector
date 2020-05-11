package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {

    @Override
    <S extends Event> S save(S entity);

    @Override
    Optional<Event> findById(Integer id);
}
