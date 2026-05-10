package com.project.project_management.mapper;

import com.project.project_management.dto.AffectationDTO;
import com.project.project_management.model.Affectation;
import com.project.project_management.model.Ressource;
import com.project.project_management.model.Tache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AffectationMapper {// Mapper pour l'affectation entre une tâche et une ressource

    public AffectationDTO toDto(Affectation affectation) {
        if (affectation == null) {
            return null;
        }

        AffectationDTO dto = new AffectationDTO();
        dto.setId(affectation.getId());
        dto.setDateDebut(affectation.getDateDebut());
        dto.setDateFin(affectation.getDateFin());

        if (affectation.getTache() != null) {
            dto.setTacheId(affectation.getTache().getId());
            dto.setTacheDescription(affectation.getTache().getDescription());
        }
        if (affectation.getRessource() != null) {
            dto.setRessourceId(affectation.getRessource().getId());
            dto.setRessourceNom(affectation.getRessource().getNom());
        }
        dto.setCout(calculCout(affectation));
        return dto;
    }

    public Affectation toEntity(AffectationDTO dto, Tache tache, Ressource ressource) {
        if (dto == null) {
            return null;
        }

        Affectation affectation = new Affectation();
        affectation.setId(dto.getId());
        affectation.setDateDebut(dto.getDateDebut());
        affectation.setDateFin(dto.getDateFin());
        affectation.setTache(tache);
        affectation.setRessource(ressource);
        return affectation;
    }

    private double calculCout(Affectation affectation) {
        if (affectation.getDateDebut() == null || affectation.getDateFin() == null ||
                affectation.getRessource() == null || affectation.getRessource().getCout() == null) {
            return 0;
        }

        long diff = affectation.getDateFin().getTime() - affectation.getDateDebut().getTime();
        long jours = Math.max(1, TimeUnit.MILLISECONDS.toDays(diff));
        return jours * affectation.getRessource().getCout();
    }
}
