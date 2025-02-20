package org.example.backendshop.repositores;

import org.example.backendshop.entites.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Integer> {
    @Query(value = "select * from manufacturer", nativeQuery = true)
    List<Object[]> getAllManufacturer();
}
