package org.example.backendshop.services;

import org.example.backendshop.entites.Color;
import org.example.backendshop.entites.ProductDetail;
import org.example.backendshop.entites.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDetailService {
    List<ProductDetail> findAll();

    <S extends ProductDetail> S save(S entity);

    Optional<ProductDetail> findById(Integer integer);

    long count();

    List<Size> findAvailableSizes(Integer idProduct, Integer idColor);

    List<Color> findAvailableColors(Integer idProduct, Integer idSize);

    List<Object[]> getAllProductDetail(Integer idPR);

    Object[] getSellingPrice(Integer idPR, Integer idS, Integer idC);
}
