import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})

export class Login {

  username = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}
  

  login() {

    const data = {
      username: this.username,
      password: this.password
    };

    this.authService.login(data).subscribe({

      next: (token) => {

        localStorage.setItem('token', token);

        this.router.navigate(['/dashboard']);

      },

      error: (err) => {

        console.log(err);

        alert('Login failed');

      }

    });

  }

}