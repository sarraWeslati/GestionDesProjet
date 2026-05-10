package com.project.project_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapportFinancierDTO { // DTO pour le rapport financier d'un projet
    private Long projetId;
    private String nomProjet;
    private Double budget;
    private Double coutTotal;
    private Double resteBudget;
    private Boolean budgetDepasse;
}
