package org.example.backendshop.repositores;

import org.example.backendshop.entites.WeightType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightTypeRepository extends JpaRepository<WeightType,Integer> {
    @Query(value = "select * from weight_type", nativeQuery = true)
    List<Object[]> getAllWeightType();
}
