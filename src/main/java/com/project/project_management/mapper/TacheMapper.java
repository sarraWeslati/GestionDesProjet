package com.project.project_management.mapper;

import com.project.project_management.dto.TacheDTO;
import com.project.project_management.model.Employe;
import com.project.project_management.model.Projet;
import com.project.project_management.model.Ressource;
import com.project.project_management.model.Tache;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacheMapper {

    // Convertit Entity -> DTO
    public TacheDTO toDto(Tache tache) {

        if (tache == null) {
            return null;
        }

        TacheDTO dto = new TacheDTO();

        dto.setId(tache.getId());
        dto.setDescription(tache.getDescription());
        dto.setEtat(tache.getEtat());
        dto.setPriorite(tache.getPriorite());
        dto.setDeadline(tache.getDeadline());

        // Projet
        if (tache.getProjet() != null) {
            dto.setProjetId(tache.getProjet().getId());
        }

        // Responsable
        if (tache.getResponsable() != null) {

            dto.setResponsableId(
                    tache.getResponsable().getId()
            );

            dto.setResponsableNom(
                    tache.getResponsable().getNom()
            );
        }

     // Ressources
        if (tache.getRessources() != null) {

            // IDs des ressources
            List<Long> ressourceIds =
                    tache.getRessources()
                            .stream()
                            .map(Ressource::getId)
                            .collect(Collectors.toList());

            dto.setRessourceIds(ressourceIds);

            // Noms des ressources
            List<String> ressourcesNoms =
                    tache.getRessources()
                            .stream()
                            .map(Ressource::getNom)
                            .collect(Collectors.toList());

            dto.setRessourcesNoms(ressourcesNoms);

            // Calcul coût total
            double total =
                    tache.getRessources()
                            .stream()
                            .mapToDouble(Ressource::getCout)
                            .sum();

            dto.setCoutTotal(total);
        }

        return dto;
    }

    // Convertit DTO -> Entity
    public Tache toEntity(
            TacheDTO dto,
            Projet projet,
            Employe responsable
    ) {

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