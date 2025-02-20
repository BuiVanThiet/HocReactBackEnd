package org.example.backendshop.services;

import org.example.backendshop.dto.request.ImageByProductRequest;
import org.example.backendshop.entites.ImageProduct;

import java.util.List;
import java.util.Optional;

public interface ImageProductService {
    List<ImageProduct> findAll();

    <S extends ImageProduct> S save(S entity);

    Optional<ImageProduct> findById(Integer integer);

    void deleteById(Integer integer);

    List<ImageByProductRequest> getListImageByProduct(Integer id);
}
