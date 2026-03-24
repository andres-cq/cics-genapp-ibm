import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../models/customer.model';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatCardModule],
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Customers</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        <table mat-table [dataSource]="customers" class="mat-elevation-z8">
          <ng-container matColumnDef="customerNumber">
            <th mat-header-cell *matHeaderCellDef>Customer #</th>
            <td mat-cell *matCellDef="let customer">{{customer.customerNumber}}</td>
          </ng-container>

          <ng-container matColumnDef="firstName">
            <th mat-header-cell *matHeaderCellDef>First Name</th>
            <td mat-cell *matCellDef="let customer">{{customer.firstName}}</td>
          </ng-container>

          <ng-container matColumnDef="lastName">
            <th mat-header-cell *matHeaderCellDef>Last Name</th>
            <td mat-cell *matCellDef="let customer">{{customer.lastName}}</td>
          </ng-container>

          <ng-container matColumnDef="dateOfBirth">
            <th mat-header-cell *matHeaderCellDef>Date of Birth</th>
            <td mat-cell *matCellDef="let customer">{{customer.dateOfBirth}}</td>
          </ng-container>

          <ng-container matColumnDef="postcode">
            <th mat-header-cell *matHeaderCellDef>Postcode</th>
            <td mat-cell *matCellDef="let customer">{{customer.postcode}}</td>
          </ng-container>

          <ng-container matColumnDef="emailAddress">
            <th mat-header-cell *matHeaderCellDef>Email</th>
            <td mat-cell *matCellDef="let customer">{{customer.emailAddress}}</td>
          </ng-container>

          <ng-container matColumnDef="actions">
            <th mat-header-cell *matHeaderCellDef>Actions</th>
            <td mat-cell *matCellDef="let customer">
              <button mat-icon-button color="primary" (click)="viewCustomer(customer)">
                <mat-icon>visibility</mat-icon>
              </button>
              <button mat-icon-button color="accent" (click)="editCustomer(customer)">
                <mat-icon>edit</mat-icon>
              </button>
              <button mat-icon-button color="warn" (click)="deleteCustomer(customer)">
                <mat-icon>delete</mat-icon>
              </button>
            </td>
          </ng-container>

          <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
        </table>
      </mat-card-content>
    </mat-card>
  `,
  styles: [`
    mat-card {
      margin: 20px;
    }
    table {
      width: 100%;
    }
  `]
})
export class CustomerListComponent implements OnInit {
  customers: Customer[] = [];
  displayedColumns: string[] = ['customerNumber', 'firstName', 'lastName', 'dateOfBirth', 'postcode', 'emailAddress', 'actions'];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getAllCustomers().subscribe({
      next: (data) => this.customers = data,
      error: (error) => console.error('Error loading customers:', error)
    });
  }

  viewCustomer(customer: Customer): void {
    console.log('View customer:', customer);
  }

  editCustomer(customer: Customer): void {
    console.log('Edit customer:', customer);
  }

  deleteCustomer(customer: Customer): void {
    if (confirm(`Delete customer ${customer.firstName} ${customer.lastName}?`)) {
      this.customerService.deleteCustomer(customer.customerNumber!).subscribe({
        next: () => this.loadCustomers(),
        error: (error) => console.error('Error deleting customer:', error)
      });
    }
  }
}

// Made with Bob
