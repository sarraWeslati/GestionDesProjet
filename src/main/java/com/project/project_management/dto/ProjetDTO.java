package com.project.project_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetDTO { // DTO pour le projet 
    private Long id;

    @NotBlank(message = "Le nom du projet est obligatoire")
    private String nom;

    @NotNull(message = "La date de debut est obligatoire")
    private Date dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private Date dateFin;

    @NotNull(message = "Le budget est obligatoire")
    @PositiveOrZero(message = "Le budget doit etre positif ou egal a zero")
    private Double budget;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;

    private List<Long> tacheIds;
    
 // IDs des ressources
    private List<Long> ressourceIds;

    // Noms des ressources
    private List<String> ressourcesNoms;

    // Coût total du projet
    private Double coutTotalProjet;

    // Budget restant
    private Double budgetRestant;
}
