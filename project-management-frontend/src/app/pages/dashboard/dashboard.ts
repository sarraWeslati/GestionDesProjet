import { Chart } from 'chart.js/auto';

import { Component, OnInit, ChangeDetectorRef } from '@angular/core';

import { CommonModule } from '@angular/common';

import { ProjetService } from '../../services/projet';

import { EmployeService } from '../../services/employe';

import { TacheService } from '../../services/tache';

import { RessourceService } from '../../services/ressource';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})

export class Dashboard implements OnInit {

  totalProjets = 0;

  totalEmployes = 0;

  totalTaches = 0;

  totalRessources = 0;

  chart: any;

  budgetChart: any;

  projetsNoms: string[] = [];

  projetsBudgets: number[] = [];

  constructor(

    private projetService: ProjetService,

    private employeService: EmployeService,

    private tacheService: TacheService,

    private ressourceService: RessourceService,

    private cdr: ChangeDetectorRef

  ) {}

  ngOnInit(): void {

    this.loadStats();

  }

  loadStats() {

    // PROJETS

    this.projetService.getAll().subscribe({

      next: (data) => {

        console.log("PROJETS :", data);

        this.totalProjets = data.length;

        this.projetsNoms = data.map(p => p.nom);

        this.projetsBudgets = data.map(
          p => p.budget || 0
        );

        this.updateChart();

        this.createBudgetChart();

        this.cdr.detectChanges();

      }

    });

    // EMPLOYES

    this.employeService.getAll().subscribe({

      next: (data) => {

        console.log("EMPLOYES :", data);

        this.totalEmployes = data.length;

        this.updateChart();

        this.cdr.detectChanges();

      }

    });

    // TACHES

    this.tacheService.getAll().subscribe({

      next: (data) => {

        console.log("TACHES :", data);

        this.totalTaches = data.length;

        this.updateChart();

        this.cdr.detectChanges();

      }

    });

    // RESSOURCES

    this.ressourceService.getAll().subscribe({

      next: (data) => {

        console.log("RESSOURCES :", data);

        this.totalRessources = data.length;

        this.updateChart();

        this.cdr.detectChanges();

      }

    });

  }

  createChart() {

    this.chart = new Chart('statsChart', {

      type: 'bar',

      data: {

        labels: [

          'Projets',
          'Employés',
          'Tâches',
          'Ressources'

        ],

        datasets: [

          {

            label: 'Statistiques Globales',

            data: [

              this.totalProjets,
              this.totalEmployes,
              this.totalTaches,
              this.totalRessources

            ]

          }

        ]

      },

      options: {

        responsive: true

      }

    });

  }

  updateChart() {

    if (!this.chart) {

      this.createChart();

      return;

    }

    this.chart.data.datasets[0].data = [

      this.totalProjets,
      this.totalEmployes,
      this.totalTaches,
      this.totalRessources

    ];

    this.chart.update();

  }

  createBudgetChart() {

    if (this.budgetChart) {

      this.budgetChart.destroy();

    }

    this.budgetChart = new Chart('budgetChart', {

      type: 'doughnut',

      data: {

        labels: this.projetsNoms,

        datasets: [

          {

            label: 'Budget Projet',

            data: this.projetsBudgets

          }

        ]

      },

      options: {

        responsive: true

      }

    });

  }

}