package org.example.backendshop.services;

import org.example.backendshop.entites.Origin;

import java.util.List;
import java.util.Optional;

public interface OriginService {
    List<Origin> findAll();

    <S extends Origin> S save(S entity);

    Optional<Origin> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllOrigin();
}
