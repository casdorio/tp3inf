package com.infnet.carlos.tp3.service;

import com.infnet.carlos.tp3.model.MaterialDidatico;
import com.infnet.carlos.tp3.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {

    private final MaterialDidaticoRepository materialDidaticoRepository;

    @Autowired
    public MaterialDidaticoService(MaterialDidaticoRepository materialDidaticoRepository) {
        this.materialDidaticoRepository = materialDidaticoRepository;
    }

    public MaterialDidatico save(MaterialDidatico materialDidatico) {
        return materialDidaticoRepository.save(materialDidatico);
    }

    public List<MaterialDidatico> findAll() {
        return materialDidaticoRepository.findAll();
    }

    public Optional<MaterialDidatico> findById(String id) {
        return materialDidaticoRepository.findById(id);
    }

    public void deleteById(String id) {
        materialDidaticoRepository.deleteById(id);
    }

    public long count() {
        return materialDidaticoRepository.count();
    }
}
