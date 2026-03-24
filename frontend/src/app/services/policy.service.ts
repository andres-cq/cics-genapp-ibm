import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Policy } from '../models/policy.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  private apiUrl = `${environment.apiUrl}/policies`;

  constructor(private http: HttpClient) {}

  getAllPolicies(): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl);
  }

  getPolicyById(id: number): Observable<Policy> {
    return this.http.get<Policy>(`${this.apiUrl}/${id}`);
  }

  getPoliciesByCustomer(customerId: number): Observable<Policy[]> {
    return this.http.get<Policy[]>(`${this.apiUrl}/customer/${customerId}`);
  }

  getPoliciesByType(policyType: string): Observable<Policy[]> {
    return this.http.get<Policy[]>(`${this.apiUrl}/type/${policyType}`);
  }

  createMotorPolicy(data: any): Observable<Policy> {
    return this.http.post<Policy>(`${this.apiUrl}/motor`, data);
  }

  createEndowmentPolicy(data: any): Observable<Policy> {
    return this.http.post<Policy>(`${this.apiUrl}/endowment`, data);
  }

  createHousePolicy(data: any): Observable<Policy> {
    return this.http.post<Policy>(`${this.apiUrl}/house`, data);
  }

  createCommercialPolicy(data: any): Observable<Policy> {
    return this.http.post<Policy>(`${this.apiUrl}/commercial`, data);
  }

  updatePolicy(id: number, policy: Policy): Observable<Policy> {
    return this.http.put<Policy>(`${this.apiUrl}/${id}`, policy);
  }

  deletePolicy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

// Made with Bob
