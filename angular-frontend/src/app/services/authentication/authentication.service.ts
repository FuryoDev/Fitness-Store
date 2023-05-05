import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:6969/api/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  //TODO: Add a class (classes) here so I don't use a body anymore and I can send more data
  login(credentials: any): Observable<any> {
    return this.http.post(AUTH_API + 'signIn', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user: any): Observable<any> {
    return this.http.post(AUTH_API + 'signUp', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
