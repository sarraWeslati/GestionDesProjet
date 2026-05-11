package com.project.project_management.mapper;

import com.project.project_management.dto.ProjetDTO;
import com.project.project_management.model.Projet;
import com.project.project_management.model.Tache;
import com.project.project_management.model.Ressource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;

@Component
public class ProjetMapper {

    public ProjetDTO toDto(Projet projet) {// Convertit une entité Projet en DTO ProjetDTO
        if (projet == null) {
            return null;
        }

        ProjetDTO dto = new ProjetDTO();
        dto.setId(projet.getId());
        dto.setNom(projet.getNom());
        dto.setDateDebut(projet.getDateDebut());
        dto.setDateFin(projet.getDateFin());
        dto.setBudget(projet.getBudget());
        dto.setStatut(projet.getStatut());
        dto.setTacheIds(projet.getTaches() == null ? Collections.emptyList() :
                projet.getTaches().stream().map(Tache::getId).collect(Collectors.toList()));
        
     // Ressources
        if (projet.getRessources() != null) {

            // IDs
            List<Long> ressourceIds =
                    projet.getRessources()
                            .stream()
                            .map(Ressource::getId)
                            .collect(Collectors.toList());

            dto.setRessourceIds(ressourceIds);

            // Noms
            List<String> ressourcesNoms =
                    projet.getRessources()
                            .stream()
                            .map(Ressource::getNom)
                            .collect(Collectors.toList());

            dto.setRessourcesNoms(ressourcesNoms);

            // Coût total projet
            double total =
                    projet.getRessources()
                            .stream()
                            .mapToDouble(Ressource::getCout)
                            .sum();

            dto.setCoutTotalProjet(total);

            // Budget restant
            if (projet.getBudget() != null) {

                dto.setBudgetRestant(
                        projet.getBudget() - total
                );
            }
        }
        return dto;
    }

    public Projet toEntity(ProjetDTO dto) {
        if (dto == null) {
            return null;
        }

        Projet projet = new Projet();
        projet.setId(dto.getId());
        projet.setNom(dto.getNom());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setBudget(dto.getBudget());
        projet.setStatut(dto.getStatut());
        return projet;
    }
}
