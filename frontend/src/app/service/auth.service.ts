import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Client } from '../model/client.model';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';
import { SignUpResponse } from '../model/SignUpResponse';
import { LoginData, LoginResponse } from '../model/LoginResponse';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl=environment.apiUrl;

  constructor(private http: HttpClient) { }
  signUp(client : Client) : Observable<SignUpResponse>{
    return this.http.post<SignUpResponse>(`${this.apiUrl}/auth/signup`,client);
  }

  login(credentials: { email: string; password: string }): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.apiUrl}/auth/login`,credentials);
  }

  setToken( token: string){
    localStorage.setItem('authToken',token);
  }

  getToken(): string | null{
    return localStorage.getItem('authToken');
  } 

  setUserData (userData : LoginData){
    localStorage.setItem('userData', JSON.stringify(userData));
  }

  getUserData() : string|null{
    const data = localStorage.getItem('userData');
    return data ? JSON.parse(data) :null;
    
  }

  isLoggedIn():boolean{
    return !! this.getToken();
  }

  logout(){
    localStorage.removeItem('authToken');
    localStorage.removeItem('userData');
  }
}
