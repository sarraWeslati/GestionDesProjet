import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RessourceService } from '../../services/ressource';
import { Ressource } from '../../models/ressource';

@Component({
  selector: 'app-ressources',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ressources.html',
  styleUrl: './ressources.css'
})

export class Ressources implements OnInit {

  editMode = false;

  ressources: Ressource[] = [];

  ressource: Ressource = {

    nom: '',
    type: '',
    cout: 0,
    disponibilite: true

  };

  constructor(private ressourceService: RessourceService) {}

  ngOnInit(): void {

    this.loadRessources();

  }

  loadRessources() {

    this.ressourceService.getAll().subscribe({

      next: (data) => {

        this.ressources = data;

      }

    });

  }

  saveRessource() {

    this.ressourceService.create(this.ressource)
      .subscribe({

        next: () => {

          this.loadRessources();

          this.resetForm();

        }

      });

  }

  editRessource(r: Ressource) {

    this.ressource = { ...r };

    this.editMode = true;

  }

  updateRessource() {

    this.ressourceService.update(this.ressource.id!, this.ressource)
      .subscribe({

        next: () => {

          this.loadRessources();

          this.resetForm();

        }

      });

  }

  deleteRessource(id: number) {

    this.ressourceService.delete(id)
      .subscribe({

        next: () => {

          this.loadRessources();

        }

      });

  }

  resetForm() {

    this.ressource = {

      nom: '',
      type: '',
      cout: 0,
      disponibilite: true

    };

    this.editMode = false;

  }

}