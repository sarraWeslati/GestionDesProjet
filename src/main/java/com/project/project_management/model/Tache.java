package com.project.project_management.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tache {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String description;
private String etat;
private String priorite;

private Date deadline;

@ManyToOne
@JoinColumn(name = "projet_id")
@JsonIgnore
private Projet projet;
@ManyToOne
private Employe responsable;
}
