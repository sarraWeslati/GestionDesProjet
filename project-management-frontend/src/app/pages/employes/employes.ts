import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { EmployeService } from '../../services/employe';
import { Employe } from '../../models/employe';

@Component({
  selector: 'app-employes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employes.html',
  styleUrl: './employes.css'
})

export class Employes implements OnInit {

  editMode = false;

  employes: Employe[] = [];

  employe: Employe = {

    nom: '',
    email: '',
    role: '',
    equipe: ''

  };

  constructor(private employeService: EmployeService) {}

  ngOnInit(): void {

    this.loadEmployes();

  }

  loadEmployes() {

    this.employeService.getAll().subscribe({

      next: (data) => {

        this.employes = data;

      }

    });

  }

  saveEmploye() {

    this.employeService.create(this.employe)
      .subscribe({

        next: () => {

          this.loadEmployes();

          this.resetForm();

        }

      });

  }

  editEmploye(e: Employe) {

    this.employe = { ...e };

    this.editMode = true;

  }

  updateEmploye() {

    this.employeService.update(this.employe.id!, this.employe)
      .subscribe({

        next: () => {

          this.loadEmployes();

          this.resetForm();

        }

      });

  }

  deleteEmploye(id: number) {

    this.employeService.delete(id)
      .subscribe({

        next: () => {

          this.loadEmployes();

        }

      });

  }

  resetForm() {

    this.employe = {

      nom: '',
      email: '',
      role: '',
      equipe: ''

    };

    this.editMode = false;

  }

}