package org.example.backendshop.implement;

import org.example.backendshop.entites.Origin;
import org.example.backendshop.repositores.OriginRepository;
import org.example.backendshop.services.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OriginImplement implements OriginService {
    @Autowired
    OriginRepository originRepository;

    @Override
    public List<Origin> findAll() {
        return originRepository.findAll();
    }

    @Override
    public <S extends Origin> S save(S entity) {
        return originRepository.save(entity);
    }

    @Override
    public Optional<Origin> findById(Integer integer) {
        return originRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        originRepository.deleteById(integer);
    }

    @Override
    public List<Object[]> getAllOrigin() {
        return originRepository.getAllOrigin();
    }
}
