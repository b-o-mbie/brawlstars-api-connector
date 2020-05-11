package com.temp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;

public interface RankingClubRepository extends CrudRepository<RankingClub, String> {

    @Override
    <S extends RankingClub> S save(S entity);

    @Override
    <S extends RankingClub> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<RankingClub> findById(String id);

    @Override
    List<RankingClub> findAll();

}
