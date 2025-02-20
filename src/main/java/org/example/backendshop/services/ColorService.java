package org.example.backendshop.services;

import org.example.backendshop.entites.Color;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<Color> findAll();

    <S extends Color> S save(S entity);

    Optional<Color> findById(Integer integer);

    long count();

    void delete(Color entity);

    List<Object[]> getAllColor();

    List<Object[]> getColorByProduct(@Param("idPR") Integer idPR);
}
