import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { PolicyService } from '../../services/policy.service';
import { Policy } from '../../models/policy.model';

@Component({
  selector: 'app-policy-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatCardModule],
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Insurance Policies</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        <table mat-table [dataSource]="policies" class="mat-elevation-z8">
          <ng-container matColumnDef="policyNumber">
            <th mat-header-cell *matHeaderCellDef>Policy #</th>
            <td mat-cell *matCellDef="let policy">{{policy.policyNumber}}</td>
          </ng-container>

          <ng-container matColumnDef="customerNumber">
            <th mat-header-cell *matHeaderCellDef>Customer #</th>
            <td mat-cell *matCellDef="let policy">{{policy.customer?.customerNumber}}</td>
          </ng-container>

          <ng-container matColumnDef="policyType">
            <th mat-header-cell *matHeaderCellDef>Type</th>
            <td mat-cell *matCellDef="let policy">{{getPolicyTypeName(policy.policyType)}}</td>
          </ng-container>

          <ng-container matColumnDef="issueDate">
            <th mat-header-cell *matHeaderCellDef>Issue Date</th>
            <td mat-cell *matCellDef="let policy">{{policy.issueDate}}</td>
          </ng-container>

          <ng-container matColumnDef="expiryDate">
            <th mat-header-cell *matHeaderCellDef>Expiry Date</th>
            <td mat-cell *matCellDef="let policy">{{policy.expiryDate}}</td>
          </ng-container>

          <ng-container matColumnDef="payment">
            <th mat-header-cell *matHeaderCellDef>Payment</th>
            <td mat-cell *matCellDef="let policy">{{policy.payment}}</td>
          </ng-container>

          <ng-container matColumnDef="actions">
            <th mat-header-cell *matHeaderCellDef>Actions</th>
            <td mat-cell *matCellDef="let policy">
              <button mat-icon-button color="primary" (click)="viewPolicy(policy)">
                <mat-icon>visibility</mat-icon>
              </button>
              <button mat-icon-button color="accent" (click)="editPolicy(policy)">
                <mat-icon>edit</mat-icon>
              </button>
              <button mat-icon-button color="warn" (click)="deletePolicy(policy)">
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
export class PolicyListComponent implements OnInit {
  policies: Policy[] = [];
  displayedColumns: string[] = ['policyNumber', 'customerNumber', 'policyType', 'issueDate', 'expiryDate', 'payment', 'actions'];

  constructor(private policyService: PolicyService) {}

  ngOnInit(): void {
    this.loadPolicies();
  }

  loadPolicies(): void {
    this.policyService.getAllPolicies().subscribe({
      next: (data) => this.policies = data,
      error: (error) => console.error('Error loading policies:', error)
    });
  }

  getPolicyTypeName(type: string | undefined): string {
    const types: { [key: string]: string } = {
      'M': 'Motor',
      'E': 'Endowment',
      'H': 'House',
      'C': 'Commercial'
    };
    return types[type || ''] || type || '';
  }

  viewPolicy(policy: Policy): void {
    console.log('View policy:', policy);
  }

  editPolicy(policy: Policy): void {
    console.log('Edit policy:', policy);
  }

  deletePolicy(policy: Policy): void {
    if (confirm(`Delete policy #${policy.policyNumber}?`)) {
      this.policyService.deletePolicy(policy.policyNumber!).subscribe({
        next: () => this.loadPolicies(),
        error: (error) => console.error('Error deleting policy:', error)
      });
    }
  }
}

// Made with Bob
