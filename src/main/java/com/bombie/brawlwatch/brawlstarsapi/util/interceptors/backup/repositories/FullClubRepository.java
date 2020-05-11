package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;

public interface FullClubRepository extends CrudRepository<FullClub, String> {

    @Override
    public <S extends FullClub> S save(S entity);

    @Override
    public Optional<FullClub> findById(String id);
}
