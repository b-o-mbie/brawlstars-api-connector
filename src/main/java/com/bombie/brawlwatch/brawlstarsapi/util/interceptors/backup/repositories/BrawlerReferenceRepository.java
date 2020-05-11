package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;

public interface BrawlerReferenceRepository extends CrudRepository<BrawlerReference, Integer> {

    @Override
    <S extends BrawlerReference> S save(S entity);

    @Override
    <S extends BrawlerReference> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<BrawlerReference> findById(Integer id);

    @Override
    List<BrawlerReference> findAll();

}
