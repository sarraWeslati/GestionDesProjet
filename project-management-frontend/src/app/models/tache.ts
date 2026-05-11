export interface Tache {

  id?: number;

  description: string;

  etat: string;

  priorite: string;

  deadline: string;

  projetId: number;

  responsableId: number;

  responsableNom?: string;

  ressourceIds?: number[];

  ressourcesNoms?: string[];

  coutTotal?: number;

}