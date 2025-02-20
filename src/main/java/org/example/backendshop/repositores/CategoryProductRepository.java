package org.example.backendshop.repositores;

import org.example.backendshop.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepository extends JpaRepository<Category,Integer> {
    @Query(value = "select * from category", nativeQuery = true)
    List<Object[]> getAllCategory();
}
