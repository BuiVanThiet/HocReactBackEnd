package org.example.backendshop.repositores;

import org.example.backendshop.entites.Color;
import org.example.backendshop.entites.ProductDetail;
import org.example.backendshop.entites.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    @Query("SELECT s FROM Size s WHERE (:idColor IS NULL OR NOT EXISTS (" +
            "SELECT 1 FROM ProductDetail pd WHERE pd.product.id = :idProduct " +
            "AND pd.color.id = :idColor AND pd.size.id = s.id))")
    List<Size> findAvailableSizes(@Param("idProduct") Integer idProduct,
                                  @Param("idColor") Integer idColor);
    // Lấy danh sách màu chưa tồn tại khi chọn size
    @Query("SELECT c FROM Color c WHERE (:idSize IS NULL OR NOT EXISTS (" +
            "SELECT 1 FROM ProductDetail pd WHERE pd.product.id = :idProduct " +
            "AND pd.size.id = :idSize AND pd.color.id = c.id))")
    List<Color> findAvailableColors(@Param("idProduct") Integer idProduct,
                                    @Param("idSize") Integer idSize);
    @Query(value = """
        select
            prd.id as 'id_product_detail',             --1
        	prd.id_size as 'id_size',                  --2
        	s.code_size as 'code_size',                --3
        	s.name_size as 'name_size',                --4
        	prd.id_color as 'id_color',                --5
        	c.code_color as 'code_color',              --6
        	c.name_color as 'name_color',              --7
        	prd.id_weight_type as 'id_weight_type',    --8
        	wt.code_weight_type as 'code_weight_type', --9
        	wt.name_weight_type as 'name_weight_type', --10
        	prd.weight as 'weight',                    --11
        	prd.import_price as 'import_price',        --12
        	prd.selling_price as 'selling_price',      --13
        	prd.quantity as 'quantity',                --14
        	prd.status as 'status'                     --15
        from product_detail prd
        left join size s on prd.id_size = s.id
        left join color c on prd.id_color = c.id
        left join weight_type wt on prd.id_weight_type = wt.id
        where prd.id_product = :idPR
""", nativeQuery = true)
    List<Object[]> getAllProductDetail(@Param("idPR") Integer idPR);

    @Query(value = """
    select MIN(selling_price),MAX(selling_price) from product_detail where id_product = :idProduct
    """, nativeQuery = true)
    Object[] getSellingPrice1(
            @Param("idProduct") Integer idProduct
    );

    @Query(value = """
            select prd.selling_price, prd.quantity from product_detail prd where prd.id_product = :idProduct and prd.id_color = :idColor and prd.id_size = :idSize
                                           """, nativeQuery = true)
    Object[] getSellingPrice2(
            @Param("idProduct") Integer idProduct,
            @Param("idColor") Integer idColor,
            @Param("idSize") Integer idSize
    );



}
