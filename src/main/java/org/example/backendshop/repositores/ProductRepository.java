package org.example.backendshop.repositores;

import org.example.backendshop.dto.response.ProductResponse;
import org.example.backendshop.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = """

            WITH ImageCTE AS (
        SELECT DISTINCT
            im.id_product,
            im.code_image
        FROM image_product im
    )
    SELECT
        pr.id AS 'id_product',
        pr.code_product AS 'code_product',
        pr.name_product AS 'name_product',
        cate.id AS 'id_category',
        cate.name_category AS 'name_category',
        manu.id AS 'id_manufacturer',
        manu.name_manufacturer AS 'name_manufacturer',
        ori.id AS 'id_origin',
        ori.name_origin AS 'name_origin',
        pr.describe AS 'describe_product',
        (SELECT TOP 1 code_image FROM ImageCTE WHERE ImageCTE.id_product = pr.id) AS 'image_product',
        pr.status AS 'status_product'
        ,MIN(prd.selling_price) AS 'min_selling_price', -- Giá thấp nhất
        MAX(prd.selling_price) AS 'max_selling_price'  -- Giá cao nhất
        ,count(prd.id_product) AS 'Quantity_product_detail'
    FROM product pr
        INNER JOIN manufacturer manu ON manu.id = pr.id_manufacturer
        INNER JOIN origin ori ON ori.id = pr.id_origin
        INNER JOIN category cate ON cate.id = pr.id_category
        LEFT JOIN image_product img ON img.id_product = pr.id
        LEFT JOIN product_detail prd ON prd.id_product = pr.id
     
    GROUP BY
        pr.id,
        pr.code_product,
        pr.name_product,
        cate.id,
        cate.name_category,
        manu.id,
        manu.name_manufacturer,
        ori.id,
        ori.name_origin,
        pr.describe,
        pr.status;
            """,nativeQuery = true)
    List<Object[]> getAllProduct();

    @Query("select new org.example.backendshop.dto.response.ProductResponse(pr.id,pr.createDate,pr.updateDate,pr.status,pr.codeProduct,pr.nameProduct,pr.category.id,pr.manufacturer.id,pr.origin.id,pr.describe) from Product pr where pr.id = :idCheck")
    ProductResponse getProductById(@Param("idCheck") Integer idCheck);

    @Query(value = """
            WITH ImageCTE AS (
        SELECT DISTINCT
            im.id_product,
            im.code_image
        FROM image_product im
    )
    SELECT
        pr.id AS 'id_product',
        pr.code_product AS 'code_product',
        pr.name_product AS 'name_product',
        cate.id AS 'id_category',
        cate.name_category AS 'name_category',
        manu.id AS 'id_manufacturer',
        manu.name_manufacturer AS 'name_manufacturer',
        ori.id AS 'id_origin',
        ori.name_origin AS 'name_origin',
        pr.describe AS 'describe_product',
        (SELECT TOP 1 code_image FROM ImageCTE WHERE ImageCTE.id_product = pr.id) AS 'image_product',
        pr.status AS 'status_product'
        ,MIN(prd.selling_price) AS 'min_selling_price', -- Giá thấp nhất
        MAX(prd.selling_price) AS 'max_selling_price'  -- Giá cao nhất
        ,count(prd.id_product) AS 'Quantity_product_detail'
    FROM product pr
        INNER JOIN manufacturer manu ON manu.id = pr.id_manufacturer
        INNER JOIN origin ori ON ori.id = pr.id_origin
        INNER JOIN category cate ON cate.id = pr.id_category
        LEFT JOIN image_product img ON img.id_product = pr.id
        LEFT JOIN product_detail prd ON prd.id_product = pr.id
    Where pr.status = 1  
    GROUP BY
        pr.id,
        pr.code_product,
        pr.name_product,
        cate.id,
        cate.name_category,
        manu.id,
        manu.name_manufacturer,
        ori.id,
        ori.name_origin,
        pr.describe,
        pr.status;
            """,nativeQuery = true)
    List<Object[]> getAllProductFromWebOrder(@Param("nameProductSearch") String nameProduct);
}
