import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Tache } from '../models/tache';

@Injectable({
  providedIn: 'root'
})

export class TacheService {

  api = 'http://localhost:8080/api/taches';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Tache[]> {

    return this.http.get<Tache[]>(this.api);

  }

  create(tache: Tache): Observable<Tache> {

    return this.http.post<Tache>(
      this.api,
      tache
    );

  }

  update(id: number, tache: Tache): Observable<Tache> {

    return this.http.put<Tache>(
      `${this.api}/${id}`,
      tache
    );

  }

  delete(id: number) {

    return this.http.delete(
      `${this.api}/${id}`
    );

  }

}