package com.project.project_management.mapper;

import com.project.project_management.dto.ProjetDTO;
import com.project.project_management.model.Projet;
import com.project.project_management.model.Tache;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

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
