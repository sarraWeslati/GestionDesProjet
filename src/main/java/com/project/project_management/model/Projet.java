package com.project.project_management.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Projet {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;

	    private Date dateDebut;
	    private Date dateFin;

	    private Double budget;

	    private String statut;

	    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
	    private List<Tache> taches;

}
