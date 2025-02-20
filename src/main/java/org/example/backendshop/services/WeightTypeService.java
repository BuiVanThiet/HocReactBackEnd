package org.example.backendshop.services;

import org.example.backendshop.entites.WeightType;

import java.util.List;
import java.util.Optional;

public interface WeightTypeService {
    List<WeightType> findAll();

    <S extends WeightType> S save(S entity);

    Optional<WeightType> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllWeightType();
}
