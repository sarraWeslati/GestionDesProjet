package com.project.project_management.service;

import com.project.project_management.model.Ressource;
import com.project.project_management.repository.RessourceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RessourceService {

    private final RessourceRepository repo;

    public RessourceService(RessourceRepository repo) {
        this.repo = repo;
    }

    public List<Ressource> getAll() {
        return repo.findAll();
    }

    public Ressource save(Ressource r) {
        return repo.save(r);
    }
}