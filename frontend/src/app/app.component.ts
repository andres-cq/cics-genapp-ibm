import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, MatToolbarModule, MatButtonModule, MatIconModule],
  template: `
    <mat-toolbar color="primary">
      <span>GenApp - Insurance Management System</span>
      <span class="spacer"></span>
      <button mat-button routerLink="/customers">
        <mat-icon>people</mat-icon>
        Customers
      </button>
      <button mat-button routerLink="/policies">
        <mat-icon>description</mat-icon>
        Policies
      </button>
    </mat-toolbar>
    <div class="container">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .spacer {
      flex: 1 1 auto;
    }
    .container {
      padding: 20px;
      max-width: 1400px;
      margin: 0 auto;
    }
  `]
})
export class AppComponent {
  title = 'GenApp';
}

// Made with Bob
