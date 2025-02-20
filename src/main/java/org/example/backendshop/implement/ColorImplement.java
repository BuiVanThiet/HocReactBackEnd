package org.example.backendshop.implement;

import org.example.backendshop.entites.Color;
import org.example.backendshop.repositores.ColorRepository;
import org.example.backendshop.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorImplement implements ColorService {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public <S extends Color> S save(S entity) {
        return colorRepository.save(entity);
    }

    @Override
    public Optional<Color> findById(Integer integer) {
        return colorRepository.findById(integer);
    }

    @Override
    public long count() {
        return colorRepository.count();
    }

    @Override
    public void delete(Color entity) {
        colorRepository.delete(entity);
    }

    @Override
    public List<Object[]> getAllColor() {
        return colorRepository.getAllColor();
    }
    @Override
    public List<Object[]> getColorByProduct(@Param("idPR") Integer idPR) {
        return this.colorRepository.getColorByProduct(idPR);
    }
}
