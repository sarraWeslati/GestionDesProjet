import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Projet } from '../models/projet';

@Injectable({
  providedIn: 'root'
})

export class ProjetService {

  api = 'http://localhost:8080/api/projets';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Projet[]> {

    return this.http.get<Projet[]>(this.api);

  }

  create(projet: Projet): Observable<Projet> {

    return this.http.post<Projet>(
      this.api,
      projet
    );

  }

  delete(id: number) {

    return this.http.delete(
      `${this.api}/${id}`
    );

  }

  update(id: number, projet: Projet) {

  return this.http.put<Projet>(
    `${this.api}/${id}`,
    projet
  );

}

}