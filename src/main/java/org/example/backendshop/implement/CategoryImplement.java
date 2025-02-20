package org.example.backendshop.implement;

import org.example.backendshop.entites.Category;
import org.example.backendshop.repositores.CategoryProductRepository;
import org.example.backendshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImplement implements CategoryService {
    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Override
    public List<Category> findAll() {
        return categoryProductRepository.findAll();
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categoryProductRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return categoryProductRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        categoryProductRepository.deleteById(integer);
    }

    @Override
    public List<Object[]> getAllCategory() {
        return categoryProductRepository.getAllCategory();
    }
}
