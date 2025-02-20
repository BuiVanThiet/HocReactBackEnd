package org.example.backendshop.repositores;

import org.example.backendshop.entites.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size,Integer> {
    @Query(value = "select * from size", nativeQuery = true)
    List<Object[]> getAllSize();

    @Query(value = """
    SELECT DISTINCT
        s.id,
        s.code_size,
        s.name_size
    FROM
        size s
    JOIN
        product_detail prd ON prd.id_size = s.id
    WHERE
        prd.id_product = :idPR
""",nativeQuery = true)
    List<Object[]> getSizeByProduct(@Param("idPR") Integer idPR);
}
