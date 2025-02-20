package org.example.backendshop.implement;

import org.example.backendshop.entites.WeightType;
import org.example.backendshop.repositores.WeightTypeRepository;
import org.example.backendshop.services.WeightTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeightTypeImplement implements WeightTypeService {
    @Autowired
    WeightTypeRepository weightTypeRepository;

    @Override
    public List<WeightType> findAll() {
        return weightTypeRepository.findAll();
    }

    @Override
    public <S extends WeightType> S save(S entity) {
        return weightTypeRepository.save(entity);
    }

    @Override
    public Optional<WeightType> findById(Integer integer) {
        return weightTypeRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        weightTypeRepository.deleteById(integer);
    }
    @Override
    public List<Object[]> getAllWeightType() {
        return weightTypeRepository.getAllWeightType();
    }
}
