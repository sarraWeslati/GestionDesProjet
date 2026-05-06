package com.project.project_management.mapper;

import com.project.project_management.dto.EmployeDTO;
import com.project.project_management.model.Employe;
import org.springframework.stereotype.Component;

@Component
public class EmployeMapper {

    public EmployeDTO toDto(Employe employe) {
        if (employe == null) {
            return null;
        }

        EmployeDTO dto = new EmployeDTO();
        dto.setId(employe.getId());
        dto.setNom(employe.getNom());
        dto.setEmail(employe.getEmail());
        dto.setRole(employe.getRole());
        dto.setEquipe(employe.getEquipe());
        return dto;
    }

    public Employe toEntity(EmployeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employe employe = new Employe();
        employe.setId(dto.getId());
        employe.setNom(dto.getNom());
        employe.setEmail(dto.getEmail());
        employe.setRole(dto.getRole());
        employe.setEquipe(dto.getEquipe());
        return employe;
    }
}
