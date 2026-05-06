package com.project.project_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationDTO {
    private Long id;

    @NotNull(message = "La date de debut est obligatoire")
    private Date dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private Date dateFin;

    @NotNull(message = "La tache est obligatoire")
    private Long tacheId;

    private String tacheDescription;

    @NotNull(message = "La ressource est obligatoire")
    private Long ressourceId;

    private String ressourceNom;
    private Double cout;
}
