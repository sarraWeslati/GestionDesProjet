package com.project.project_management.mapper;

import com.project.project_management.dto.RessourceDTO;
import com.project.project_management.model.Ressource;
import org.springframework.stereotype.Component;

@Component
public class RessourceMapper {

    public RessourceDTO toDto(Ressource ressource) {// Convertit une entité Ressource en DTO RessourceDTO
        if (ressource == null) {
            return null;
        }

        RessourceDTO dto = new RessourceDTO();
        dto.setId(ressource.getId());
        dto.setNom(ressource.getNom());
        dto.setType(ressource.getType());
        dto.setCout(ressource.getCout());
        dto.setDisponibilite(ressource.getDisponibilite());
        return dto;
    }

    public Ressource toEntity(RessourceDTO dto) {
        if (dto == null) {
            return null;
        }

        Ressource ressource = new Ressource();
        ressource.setId(dto.getId());
        ressource.setNom(dto.getNom());
        ressource.setType(dto.getType());
        ressource.setCout(dto.getCout());
        ressource.setDisponibilite(dto.getDisponibilite());
        return ressource;
    }
}
