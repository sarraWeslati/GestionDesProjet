package com.project.project_management.service;

import com.project.project_management.dto.TacheDTO;
import com.project.project_management.mapper.TacheMapper;
import com.project.project_management.model.Employe;
import com.project.project_management.model.Projet;
import com.project.project_management.model.Tache;
import com.project.project_management.repository.EmployeRepository;
import com.project.project_management.repository.ProjetRepository;
import com.project.project_management.repository.TacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TacheService {

    private final TacheRepository tacheRepository;
    private final ProjetRepository projetRepository;
    private final EmployeRepository employeRepository;
    private final TacheMapper tacheMapper;

    public TacheService(TacheRepository tacheRepository, ProjetRepository projetRepository,
                        EmployeRepository employeRepository, TacheMapper tacheMapper) {
        this.tacheRepository = tacheRepository;
        this.projetRepository = projetRepository;
        this.employeRepository = employeRepository;
        this.tacheMapper = tacheMapper;
    }

    public List<TacheDTO> getAll() {
        return tacheRepository.findAll()
                .stream()
                .map(tacheMapper::toDto)
                .collect(Collectors.toList());
    }

    public TacheDTO getById(Long id) {
        return tacheMapper.toDto(getEntityById(id));
    }

    public TacheDTO save(TacheDTO dto) {
        Tache tache = buildEntity(dto);
        return tacheMapper.toDto(tacheRepository.save(tache));
    }

    public TacheDTO update(Long id, TacheDTO dto) {
        Tache tache = buildEntity(dto);
        tache.setId(id);
        return tacheMapper.toDto(tacheRepository.save(tache));
    }

    public void delete(Long id) {
        tacheRepository.deleteById(id);
    }

    private Tache buildEntity(TacheDTO dto) {
        Projet projet = dto.getProjetId() == null ? null : projetRepository.findById(dto.getProjetId())
                .orElseThrow(() -> new RuntimeException("Projet not found"));
        Employe responsable = dto.getResponsableId() == null ? null : employeRepository.findById(dto.getResponsableId())
                .orElseThrow(() -> new RuntimeException("Employe not found"));
        return tacheMapper.toEntity(dto, projet, responsable);
    }

    public Tache getEntityById(Long id) {
        return tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache not found"));
    }
}
