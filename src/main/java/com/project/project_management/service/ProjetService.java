package com.project.project_management.service;

import com.project.project_management.model.Affectation;
import com.project.project_management.model.Projet;
import com.project.project_management.repository.AffectationRepository;
import com.project.project_management.repository.ProjetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final AffectationRepository affectationRepo;

    public ProjetService(ProjetRepository projetRepository, AffectationRepository affectationRepo) {
        this.projetRepository = projetRepository;
        this.affectationRepo = affectationRepo;
    }

    public List<Projet> getAll() {
        return projetRepository.findAll();
    }

    public Projet save(Projet projet) {
        return projetRepository.save(projet);
    }

    public void delete(Long id) {
        projetRepository.deleteById(id);
    }
    
    public double calculCoutProjet(Long projetId) {

        List<Affectation> affectations = affectationRepo.findAll();

        double total = 0;

        for (Affectation a : affectations) {
            if (a.getTache().getProjet().getId().equals(projetId)) {

                long diff = a.getDateFin().getTime() - a.getDateDebut().getTime();
                long jours = diff / (1000 * 60 * 60 * 24);

                total += jours * a.getRessource().getCout();
            }
        }

        return total;
    }
    
    public Projet getById(Long id) {
        return projetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Projet not found"));
    }
}