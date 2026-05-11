import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { ProjetService } from '../../services/projet';

import { Projet } from '../../models/projet';

import { Ressource } from '../../models/ressource';

import { RessourceService } from '../../services/ressource';

@Component({
  selector: 'app-projets',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './projets.html',
  styleUrl: './projets.css'
})

export class Projets implements OnInit {

  editMode = false;

  projets: Projet[] = [];

  ressources: Ressource[] = [];

  projet: Projet = {

    nom: '',
    dateDebut: '',
    dateFin: '',
    budget: 0,
    statut: '',
    ressourceIds: []

  };

  constructor(
    private projetService: ProjetService,
    private ressourceService: RessourceService
  ) {}

  ngOnInit(): void {

    this.loadProjets();

    this.loadRessources();

  }

  // CHARGER PROJETS

  loadProjets() {

    this.projetService.getAll().subscribe({

      next: (data) => {

        this.projets = data;

      },

      error: (err) => {

        console.log(err);

      }

    });

  }

  // AJOUT PROJET

  saveProjet() {

    console.log("DONNEES ENVOYEES :", this.projet);

    const projetToSend = {

      nom: this.projet.nom,
      dateDebut: this.projet.dateDebut,
      dateFin: this.projet.dateFin,
      budget: this.projet.budget,
      statut: this.projet.statut,

      ressourceIds: this.projet.ressourceIds

    };

    this.projetService.create(projetToSend as Projet)
      .subscribe({

        next: () => {

          console.log("Projet ajouté");

          this.loadProjets();

          this.resetForm();

        },

        error: (err) => {

          console.log(err);

        }

      });

  }

  // EDITION

  editProjet(p: Projet) {

    this.projet = {

      ...p,

      ressourceIds: p.ressourceIds || []

    };

    this.editMode = true;

  }

  // UPDATE

  updateProjet() {

    const projetToSend = {

      nom: this.projet.nom,
      dateDebut: this.projet.dateDebut,
      dateFin: this.projet.dateFin,
      budget: this.projet.budget,
      statut: this.projet.statut,

      ressourceIds: this.projet.ressourceIds

    };

    console.log("UPDATE :", projetToSend);

    this.projetService.update(
      this.projet.id!,
      projetToSend as Projet
    )
    .subscribe({

      next: () => {

        console.log("Projet modifié");

        this.loadProjets();

        this.resetForm();

      },

      error: (err) => {

        console.log(err);

      }

    });

  }

  // DELETE

  deleteProjet(id: number) {

    this.projetService.delete(id)
      .subscribe({

        next: () => {

          console.log("Projet supprimé");

          this.loadProjets();

        },

        error: (err) => {

          console.log(err);

        }

      });

  }

  // RESET FORM

  resetForm() {

    this.projet = {

      nom: '',
      dateDebut: '',
      dateFin: '',
      budget: 0,
      statut: '',
      ressourceIds: []

    };

    this.editMode = false;

  }

  // CHARGER RESSOURCES

  loadRessources() {

    this.ressourceService.getAll().subscribe({

      next: (data) => {

        this.ressources = data;

      },

      error: (err) => {

        console.log(err);

      }

    });

  }

}