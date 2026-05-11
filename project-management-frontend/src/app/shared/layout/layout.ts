import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})

export class Layout {

  constructor(private router: Router) {}

  logout() {

    localStorage.removeItem('token');

    this.router.navigate(['/login']);

  }

}