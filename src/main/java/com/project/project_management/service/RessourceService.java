package com.project.project_management.service;

import com.project.project_management.dto.RessourceDTO;
import com.project.project_management.mapper.RessourceMapper;
import com.project.project_management.model.Ressource;
import com.project.project_management.repository.RessourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RessourceService {

    private final RessourceRepository repo;
    private final RessourceMapper ressourceMapper;

    public RessourceService(RessourceRepository repo, RessourceMapper ressourceMapper) {
        this.repo = repo;
        this.ressourceMapper = ressourceMapper;
    }

    public List<RessourceDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ressourceMapper::toDto)
                .collect(Collectors.toList());
    }

    public RessourceDTO getById(Long id) {
        return ressourceMapper.toDto(getEntityById(id));
    }

    public RessourceDTO save(RessourceDTO dto) {
        return ressourceMapper.toDto(repo.save(ressourceMapper.toEntity(dto)));
    }

    public RessourceDTO update(Long id, RessourceDTO dto) {
        Ressource ressource = ressourceMapper.toEntity(dto);
        ressource.setId(id);
        return ressourceMapper.toDto(repo.save(ressource));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Ressource getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource not found"));
    }
}
