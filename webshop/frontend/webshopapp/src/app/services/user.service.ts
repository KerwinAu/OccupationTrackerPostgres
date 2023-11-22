// user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserEntity } from '../model/user.entity.model'; // Make sure to create this model

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/UserEntity';

  constructor(private http: HttpClient) { }

  createUser(user: UserEntity): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}`, user);
  }

  getAllUsers(): Observable<UserEntity[]> {
    return this.http.get<UserEntity[]>(`${this.baseUrl}`);
  }

  deleteUser(userId: number): Observable<boolean> {
    // Construct the correct URL for deletion
    const deleteUrl = `${this.baseUrl}/delete?id=${userId}`;

    // Make the DELETE request
    return this.http.delete<boolean>(deleteUrl);
  }

  getCalcTable(day: string | null, timeofday: string | null): Observable<any> {
    // Construct the base URL
    let url = `${this.baseUrl}/getCalcTable`;
    // Add parameters if they are provided and not null or empty
    if (day && day.trim() !== '') {
      url += `?selectedDay=${day}`;
    }
    if (timeofday && timeofday.trim() !== '') {
      // Use '&' if there's already a parameter
      url += (url.includes('?') ? '&' : '?') + `selectedTimeOfDay=${timeofday}`;
    }
    return this.http.get(url);
  }

  getCurrentStatus(): Observable<any> {
    // Construct the base URL
    let url = `${this.baseUrl}/getCurrentStatus`;
    return this.http.get(url);

  }


}
