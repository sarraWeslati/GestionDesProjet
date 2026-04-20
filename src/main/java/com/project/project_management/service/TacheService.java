package com.project.project_management.service;

import com.project.project_management.model.Tache;
import com.project.project_management.repository.TacheRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TacheService {

    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public List<Tache> getAll() {
        return tacheRepository.findAll();
    }

    public Tache save(Tache t) {
        return tacheRepository.save(t);
    }

    public void delete(Long id) {
        tacheRepository.deleteById(id);
    }
}