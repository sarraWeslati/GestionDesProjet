package com.project.project_management.service;

import com.project.project_management.dto.AffectationDTO;
import com.project.project_management.mapper.AffectationMapper;
import com.project.project_management.model.Affectation;
import com.project.project_management.model.Ressource;
import com.project.project_management.model.Tache;
import com.project.project_management.repository.AffectationRepository;
import com.project.project_management.repository.RessourceRepository;
import com.project.project_management.repository.TacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AffectationService {

    private final AffectationRepository affectationRepo;
    private final TacheRepository tacheRepo;
    private final RessourceRepository ressourceRepo;
    private final AffectationMapper affectationMapper;

    public AffectationService(AffectationRepository affectationRepo,
                              TacheRepository tacheRepo,
                              RessourceRepository ressourceRepo,
                              AffectationMapper affectationMapper) {
        this.affectationRepo = affectationRepo;
        this.tacheRepo = tacheRepo;
        this.ressourceRepo = ressourceRepo;
        this.affectationMapper = affectationMapper;
    }

    public List<AffectationDTO> getAll() {
        return affectationRepo.findAll()
                .stream()
                .map(affectationMapper::toDto)
                .collect(Collectors.toList());
    }

    public AffectationDTO getById(Long id) {
        return affectationMapper.toDto(getEntityById(id));
    }

    public AffectationDTO save(AffectationDTO dto) {
        Tache tache = tacheRepo.findById(dto.getTacheId())
                .orElseThrow(() -> new RuntimeException("Tache not found"));
        Ressource ressource = ressourceRepo.findById(dto.getRessourceId())
                .orElseThrow(() -> new RuntimeException("Ressource not found"));

        Affectation affectation = affectationMapper.toEntity(dto, tache, ressource);
        return affectationMapper.toDto(affectationRepo.save(affectation));
    }

    public AffectationDTO update(Long id, AffectationDTO dto) {
        dto.setId(id);
        return save(dto);
    }

    public void delete(Long id) {
        affectationRepo.deleteById(id);
    }

    public double calculCout(Affectation affectation) {
        long diff = affectation.getDateFin().getTime() - affectation.getDateDebut().getTime();
        long jours = Math.max(1, diff / (1000 * 60 * 60 * 24));
        return jours * affectation.getRessource().getCout();
    }

    public Affectation getEntityById(Long id) {
        return affectationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Affectation not found"));
    }
}
