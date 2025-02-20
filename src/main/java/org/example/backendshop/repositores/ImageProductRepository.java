package org.example.backendshop.repositores;

import org.example.backendshop.dto.request.ImageByProductRequest;
import org.example.backendshop.entites.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct,Integer> {
    @Query("select new org.example.backendshop.dto.request.ImageByProductRequest(img.id,'img.png','done',img.codeImage ) from ImageProduct img where img.product.id = :idCheck")
    List<ImageByProductRequest> getListImageByProduct(@Param("idCheck") Integer id);
}
