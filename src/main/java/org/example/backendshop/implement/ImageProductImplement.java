package org.example.backendshop.implement;

import org.example.backendshop.dto.request.ImageByProductRequest;
import org.example.backendshop.entites.ImageProduct;
import org.example.backendshop.repositores.ImageProductRepository;
import org.example.backendshop.services.ImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageProductImplement implements ImageProductService {
    @Autowired
    ImageProductRepository productRepository;

    @Override
    public List<ImageProduct> findAll() {
        return productRepository.findAll();
    }

    @Override
    public <S extends ImageProduct> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<ImageProduct> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public List<ImageByProductRequest> getListImageByProduct(Integer id) {
        return productRepository.getListImageByProduct(id);
    }
}
