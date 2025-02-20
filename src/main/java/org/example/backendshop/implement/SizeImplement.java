package org.example.backendshop.implement;

import org.example.backendshop.entites.Size;
import org.example.backendshop.repositores.SizeRepository;
import org.example.backendshop.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeImplement implements SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public <S extends Size> S save(S entity) {
        return sizeRepository.save(entity);
    }

    @Override
    public Optional<Size> findById(Integer integer) {
        return sizeRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        sizeRepository.deleteById(integer);
    }

    @Override
    public List<Object[]> getAllSize() {
        return sizeRepository.getAllSize();
    }

    @Override
    public List<Object[]> getSizeByProduct(Integer idPR) {
        return this.sizeRepository.getSizeByProduct(idPR);
    }

}
