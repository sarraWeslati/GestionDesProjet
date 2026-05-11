import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Ressource } from '../models/ressource';

@Injectable({
  providedIn: 'root'
})

export class RessourceService {

  api = 'http://localhost:8080/api/ressources';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Ressource[]> {

    return this.http.get<Ressource[]>(this.api);

  }

  create(ressource: Ressource): Observable<Ressource> {

    return this.http.post<Ressource>(
      this.api,
      ressource
    );

  }

  update(id: number, ressource: Ressource): Observable<Ressource> {

    return this.http.put<Ressource>(
      `${this.api}/${id}`,
      ressource
    );

  }

  delete(id: number) {

    return this.http.delete(
      `${this.api}/${id}`
    );

  }

}