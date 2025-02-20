package org.example.backendshop.repositores;

import org.example.backendshop.entites.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginRepository extends JpaRepository<Origin,Integer> {
    @Query(value = "select * from origin", nativeQuery = true)
    List<Object[]> getAllOrigin();
}
