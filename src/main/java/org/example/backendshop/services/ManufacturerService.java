package org.example.backendshop.services;

import org.example.backendshop.entites.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    <S extends Manufacturer> S save(S entity);

    Optional<Manufacturer> findById(Integer integer);

    void deleteById(Integer integer);

    List<Object[]> getAllManufacturer();
}
