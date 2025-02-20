package org.example.backendshop.implement;

import org.example.backendshop.entites.Color;
import org.example.backendshop.entites.ProductDetail;
import org.example.backendshop.entites.Size;
import org.example.backendshop.repositores.ProductDetailRepository;
import org.example.backendshop.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailImplement implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public <S extends ProductDetail> S save(S entity) {
        return productDetailRepository.save(entity);
    }

    @Override
    public Optional<ProductDetail> findById(Integer integer) {
        return productDetailRepository.findById(integer);
    }

    @Override
    public long count() {
        return productDetailRepository.count();
    }

    @Override
    public List<Size> findAvailableSizes(Integer idProduct, Integer idColor) {
        return this.productDetailRepository.findAvailableSizes(idProduct,idColor);
    }

    @Override
    public List<Color> findAvailableColors(Integer idProduct, Integer idSize) {
        return this.productDetailRepository.findAvailableColors(idProduct,idSize);
    }

    @Override
    public List<Object[]> getAllProductDetail(Integer idPR) {
        return this.productDetailRepository.getAllProductDetail(idPR);
    }

    @Override
    public Object[] getSellingPrice(Integer idPR, Integer idS, Integer idC) {
        if(idS != null && idC != null) {
            return this.productDetailRepository.getSellingPrice2(idPR,idC,idS);
        }else {
            return this.productDetailRepository.getSellingPrice1(idPR);
        }
    }
}
