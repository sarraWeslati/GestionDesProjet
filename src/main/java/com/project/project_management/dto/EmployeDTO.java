package com.project.project_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO { // DTO pour l'employé 
    private Long id;

    @NotBlank(message = "Le nom de l'employe est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit etre valide")
    private String email;

    @NotBlank(message = "Le role est obligatoire")
    private String role;

    @NotBlank(message = "L'equipe est obligatoire")
    private String equipe;
}
