import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Employe } from '../models/employe';

@Injectable({
  providedIn: 'root'
})

export class EmployeService {

  api = 'http://localhost:8080/api/employes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Employe[]> {

    return this.http.get<Employe[]>(this.api);

  }

  create(employe: Employe): Observable<Employe> {

    return this.http.post<Employe>(
      this.api,
      employe
    );

  }

  update(id: number, employe: Employe): Observable<Employe> {

    return this.http.put<Employe>(
      `${this.api}/${id}`,
      employe
    );

  }

  delete(id: number) {

    return this.http.delete(
      `${this.api}/${id}`
    );

  }

}