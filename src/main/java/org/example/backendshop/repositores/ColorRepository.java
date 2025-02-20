package org.example.backendshop.repositores;

import org.example.backendshop.entites.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {
    @Query(value = "select * from color", nativeQuery = true)
    List<Object[]> getAllColor();
    @Query(value = """
    SELECT DISTINCT
        cl.id,
        cl.code_color,
        cl.name_color
    FROM
        color cl
    JOIN
        product_detail prd ON prd.id_color = cl.id
    WHERE
        prd.id_product = :idPR
""",nativeQuery = true)
    List<Object[]> getColorByProduct(@Param("idPR") Integer idPR);
}
