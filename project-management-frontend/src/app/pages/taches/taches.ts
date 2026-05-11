import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TacheService } from '../../services/tache';
import { ProjetService } from '../../services/projet';
import { EmployeService } from '../../services/employe';


import { Tache } from '../../models/tache';
import { Projet } from '../../models/projet';
import { Employe } from '../../models/employe';
import { Ressource } from '../../models/ressource';

import { RessourceService } from '../../services/ressource';

@Component({
  selector: 'app-taches',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './taches.html',
  styleUrl: './taches.css'
})

export class Taches implements OnInit {

  editMode = false;

  taches: Tache[] = [];

  projets: Projet[] = [];

  employes: Employe[] = [];

  ressources: Ressource[] = [];

  tache: Tache = {

   description: '',
  etat: '',
  priorite: '',
  deadline: '',
  projetId: 0,
  responsableId: 0,

  ressourceIds: []

};

  constructor(
    private tacheService: TacheService,
    private projetService: ProjetService,
    private employeService: EmployeService,
    private ressourceService: RessourceService
  ) {}

  ngOnInit(): void {

    this.loadTaches();

    this.loadProjets();

    this.loadEmployes();

    this.loadRessources();

  }

  loadTaches() {

    this.tacheService.getAll().subscribe({

      next: (data) => {

        this.taches = data;

      }

    });

  }

  loadProjets() {

    this.projetService.getAll().subscribe({

      next: (data) => {

        this.projets = data;

      }

    });

  }

  loadEmployes() {

    this.employeService.getAll().subscribe({

      next: (data) => {

        this.employes = data;

      }

    });

  }

  loadRessources() {

  this.ressourceService.getAll().subscribe({

    next: (data) => {

      this.ressources = data;

    }

  });

}

  saveTache() {

    this.tacheService.create(this.tache)
      .subscribe({

        next: () => {

          this.loadTaches();

          this.resetForm();

        }

      });

  }

  editTache(t: Tache) {

    this.tache = { ...t };

    this.editMode = true;

  }

  updateTache() {

    this.tacheService.update(this.tache.id!, this.tache)
      .subscribe({

        next: () => {

          this.loadTaches();

          this.resetForm();

        }

      });

  }

  deleteTache(id: number) {

    this.tacheService.delete(id)
      .subscribe({

        next: () => {

          this.loadTaches();

        }

      });

  }

  resetForm() {

   this.tache = {

   description: '',
  etat: '',
  priorite: '',
  deadline: '',
  projetId: 0,
  responsableId: 0,

  ressourceIds: []

};

    this.editMode = false;

  }

}