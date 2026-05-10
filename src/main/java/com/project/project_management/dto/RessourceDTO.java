package com.project.project_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RessourceDTO { // DTO pour la ressource
    private Long id;

    @NotBlank(message = "Le nom de la ressource est obligatoire")
    private String nom;

    @NotBlank(message = "Le type de ressource est obligatoire")
    private String type;

    @NotNull(message = "Le cout est obligatoire")
    @PositiveOrZero(message = "Le cout doit etre positif ou egal a zero")
    private Double cout;

    @NotNull(message = "La disponibilite est obligatoire")
    private Boolean disponibilite;
}
