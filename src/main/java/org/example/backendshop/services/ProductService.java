package org.example.backendshop.services;

import org.example.backendshop.dto.response.ProductResponse;
import org.example.backendshop.entites.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    <S extends Product> S save(S entity);

    Optional<Product> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllProduct();

    String uploadFile(MultipartFile multipartFile,Product productUpload) throws IOException;

    String deleteFileImage(String publicId) throws IOException;

    ProductResponse getProductById(Integer idCheck);
}
