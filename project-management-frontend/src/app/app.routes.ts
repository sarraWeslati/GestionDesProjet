import { Routes } from '@angular/router';

import { Login } from './pages/login/login';

import { Dashboard } from './pages/dashboard/dashboard';
import { Projets } from './pages/projets/projets';
import { Employes } from './pages/employes/employes';
import { Taches } from './pages/taches/taches';
import { Ressources } from './pages/ressources/ressources';


import { Layout } from './shared/layout/layout';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: Login
  },

  {
    path: '',
    component: Layout,


    children: [

      {
        path: 'dashboard',
        component: Dashboard
      },

      {
        path: 'projets',
        component: Projets
      },

      {
        path: 'employes',
        component: Employes
      },

      {
        path: 'taches',
        component: Taches
      },

      {
        path: 'ressources',
        component: Ressources
      }

    ]

  }

];