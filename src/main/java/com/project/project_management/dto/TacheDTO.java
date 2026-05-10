package com.project.project_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacheDTO { // DTO pour la tâche 
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "L'etat est obligatoire")
    private String etat;

    @NotBlank(message = "La priorite est obligatoire")
    private String priorite;

    @NotNull(message = "La deadline est obligatoire")
    private Date deadline;

    @NotNull(message = "Le projet est obligatoire")
    private Long projetId;

    @NotNull(message = "Le responsable est obligatoire")
    private Long responsableId;

    private String responsableNom;
    
    private List<Long> ressourceIds;
    
    // Noms des ressources
    private List<String> ressourcesNoms;

    // Coût total de la tâche
    private double coutTotal;
}
