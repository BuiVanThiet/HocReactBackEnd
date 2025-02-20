package org.example.backendshop.services;

import org.example.backendshop.entites.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllCategory();
}
