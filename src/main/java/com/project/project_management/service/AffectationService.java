package com.project.project_management.service;

import com.project.project_management.model.*;
import com.project.project_management.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationService {

    private final AffectationRepository affectationRepo;
    private final TacheRepository tacheRepo;
    private final RessourceRepository ressourceRepo;

    public AffectationService(AffectationRepository affectationRepo,
                              TacheRepository tacheRepo,
                              RessourceRepository ressourceRepo) {
        this.affectationRepo = affectationRepo;
        this.tacheRepo = tacheRepo;
        this.ressourceRepo = ressourceRepo;
    }

    public List<Affectation> getAll() {
        return affectationRepo.findAll();
    }

    public Affectation save(Affectation a) {

        // 🔴 récupérer les vraies entités depuis DB
        Tache tache = tacheRepo.findById(a.getTache().getId())
                .orElseThrow(() -> new RuntimeException("Tache not found"));

        Ressource ressource = ressourceRepo.findById(a.getRessource().getId())
                .orElseThrow(() -> new RuntimeException("Ressource not found"));

        // 🔁 réassigner
        a.setTache(tache);
        a.setRessource(ressource);

        return affectationRepo.save(a);
    }
    
    public double calculCout(Affectation a) {
        long diff = a.getDateFin().getTime() - a.getDateDebut().getTime();
        long jours = diff / (1000 * 60 * 60 * 24);

        return jours * a.getRessource().getCout();
    }
}