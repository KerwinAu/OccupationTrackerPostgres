import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserEntity } from '../model/user.entity.model'; // Import the UserEntity model

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Base URL for the backend service
  private baseUrl = 'https://8081-kerwinau-occupationtrac-no97vhlbyvh.ws-eu107.gitpod.io';

  constructor(private http: HttpClient) { }

  // Method to create a new user
  createUser(user: UserEntity): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}`, user);
  }

  // Method to get all users
  getAllUsers(): Observable<UserEntity[]> {
    return this.http.get<UserEntity[]>(`${this.baseUrl}`);
  }

  // Method to delete a user
  deleteUser(userId: number): Observable<boolean> {
    const deleteUrl = `${this.baseUrl}/delete?id=${userId}`;
    return this.http.delete<boolean>(deleteUrl);
  }

  // Method to get the calculation table
  getCalcTable(day: string | null, timeofday: string | null): Observable<any> {
    let url = `${this.baseUrl}/getCalcTable`;
    if (day && day.trim() !== '') {
      url += `?selectedDay=${day}`;
    }
    if (timeofday && timeofday.trim() !== '') {
      url += (url.includes('?') ? '&' : '?') + `selectedTimeOfDay=${timeofday}`;
    }
    return this.http.get(url);
  }

  // Method to get the current status
  getCurrentStatus(): Observable<any> {
    let url = `${this.baseUrl}/getCurrentStatus`;
    return this.http.get(url);
  }

  getMonthlyRecords(month: string | null): Observable<any> {
    let url = `${this.baseUrl}/getMonthlyRecords`;
    if (month && month.trim() !== '') {
      url += `?selectedMonth=${month}`;
    }
    return this.http.get(url);
  }

}