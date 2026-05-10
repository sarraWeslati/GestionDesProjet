package com.project.project_management.mapper;

import com.project.project_management.dto.TacheDTO;
import com.project.project_management.model.Employe;
import com.project.project_management.model.Projet;
import com.project.project_management.model.Tache;
import org.springframework.stereotype.Component;

@Component
public class TacheMapper {

    public TacheDTO toDto(Tache tache) {// Convertit une entité Tache en DTO TacheDTO
        if (tache == null) {
            return null;
        }

        TacheDTO dto = new TacheDTO();
        dto.setId(tache.getId());
        dto.setDescription(tache.getDescription());
        dto.setEtat(tache.getEtat());
        dto.setPriorite(tache.getPriorite());
        dto.setDeadline(tache.getDeadline());

        if (tache.getProjet() != null) {
            dto.setProjetId(tache.getProjet().getId());
        }
        if (tache.getResponsable() != null) {
            dto.setResponsableId(tache.getResponsable().getId());
            dto.setResponsableNom(tache.getResponsable().getNom());
        }
        return dto;
    }

    public Tache toEntity(TacheDTO dto, Projet projet, Employe responsable) {
        if (dto == null) {
            return null;
        }

        Tache tache = new Tache();
        tache.setId(dto.getId());
        tache.setDescription(dto.getDescription());
        tache.setEtat(dto.getEtat());
        tache.setPriorite(dto.getPriorite());
        tache.setDeadline(dto.getDeadline());
        tache.setProjet(projet);
        tache.setResponsable(responsable);
        return tache;
    }
}
