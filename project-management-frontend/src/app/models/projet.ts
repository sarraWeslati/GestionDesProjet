export interface Projet {

  id?: number;

  nom: string;

  dateDebut: string;

  dateFin: string;

  budget: number;

  statut: string;

  // Ressources sélectionnées
  ressourceIds?: number[];

  // Noms des ressources
  ressourcesNoms?: string[];

  // Coût total projet
  coutTotalProjet?: number;

  // Budget restant
  budgetRestant?: number;
}