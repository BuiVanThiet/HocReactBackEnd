package org.example.backendshop.services;

import org.example.backendshop.entites.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    List<Size> findAll();

    <S extends Size> S save(S entity);

    Optional<Size> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllSize();

    List<Object[]> getSizeByProduct(Integer idPR);
}
