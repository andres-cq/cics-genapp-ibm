import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/customers',
    pathMatch: 'full'
  },
  {
    path: 'customers',
    loadComponent: () => import('./components/customer/customer-list.component').then(m => m.CustomerListComponent)
  },
  {
    path: 'policies',
    loadComponent: () => import('./components/policy/policy-list.component').then(m => m.PolicyListComponent)
  }
];

// Made with Bob
