package com.project.project_management.service;

import com.project.project_management.dto.ProjetDTO;
import com.project.project_management.dto.RapportFinancierDTO;
import com.project.project_management.mapper.ProjetMapper;
import com.project.project_management.model.Affectation;
import com.project.project_management.model.Projet;
import com.project.project_management.repository.AffectationRepository;
import com.project.project_management.repository.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final AffectationRepository affectationRepo;
    private final ProjetMapper projetMapper;

    public ProjetService(ProjetRepository projetRepository, AffectationRepository affectationRepo,
                         ProjetMapper projetMapper) {
        this.projetRepository = projetRepository;
        this.affectationRepo = affectationRepo;
        this.projetMapper = projetMapper;
    }

    public List<ProjetDTO> getAll() {
        return projetRepository.findAll()
                .stream()
                .map(projetMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProjetDTO save(ProjetDTO dto) {
        Projet projet = projetMapper.toEntity(dto);
        return projetMapper.toDto(projetRepository.save(projet));
    }

    public ProjetDTO update(Long id, ProjetDTO dto) {
        Projet projet = getEntityById(id);
        projet.setNom(dto.getNom());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setBudget(dto.getBudget());
        projet.setStatut(dto.getStatut());
        return projetMapper.toDto(projetRepository.save(projet));
    }

    public void delete(Long id) {
        projetRepository.deleteById(id);
    }
    
    public double calculCoutProjet(Long projetId) {

        List<Affectation> affectations = affectationRepo.findAll();

        double total = 0;

        for (Affectation a : affectations) {
            if (a.getTache() != null && a.getTache().getProjet() != null &&
                    a.getTache().getProjet().getId().equals(projetId)) {

                long diff = a.getDateFin().getTime() - a.getDateDebut().getTime();
                long jours = Math.max(1, diff / (1000 * 60 * 60 * 24));

                total += jours * a.getRessource().getCout();
            }
        }

        return total;
    }
    
    public ProjetDTO getById(Long id) {
        return projetMapper.toDto(getEntityById(id));
    }

    public RapportFinancierDTO getRapportFinancier(Long id) {
        Projet projet = getEntityById(id);
        double cout = calculCoutProjet(id);
        double budget = projet.getBudget() == null ? 0 : projet.getBudget();
        return new RapportFinancierDTO(
                projet.getId(),
                projet.getNom(),
                budget,
                cout,
                budget - cout,
                cout > budget
        );
    }

    public Projet getEntityById(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet not found"));
    }
}
