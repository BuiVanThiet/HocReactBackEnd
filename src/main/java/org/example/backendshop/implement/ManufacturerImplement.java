package org.example.backendshop.implement;

import org.example.backendshop.entites.Manufacturer;
import org.example.backendshop.repositores.ManufacturerRepository;
import org.example.backendshop.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerImplement implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public <S extends Manufacturer> S save(S entity) {
        return manufacturerRepository.save(entity);
    }

    @Override
    public Optional<Manufacturer> findById(Integer integer) {
        return manufacturerRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        manufacturerRepository.deleteById(integer);
    }

    @Override
    public List<Object[]> getAllManufacturer() {
        return manufacturerRepository.getAllManufacturer();
    }
}
