import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class JwtClientService {
  email!: any;
  userRole!: any;
  userDetails!: any;
  userName!: any;
  token!: String;


  constructor(private http: HttpClient) {
    const localToken = localStorage.getItem('token');

    // TODO: check if token expired
    // If yes - delete from local storage
    if (localToken) {
      this.setToken(localToken);
    }
  }

  public setUserDetails(value: any) {
    this.userDetails = value;
    this.email = this.userDetails.email;
    this.userRole = this.userDetails.role;
    this.userName = this.userDetails.name;

  }
  public getUserDetails() {
    return this.userDetails;
  }

  public setUserRole(value: any) {
    this.userRole = value;
  }
  public getUserRole() {
    return this.userRole;
  }

  public setEmail(value: any) {
    this.email = value;

  }

  public setToken(token: string) {
    this.token = token;
    localStorage.setItem('token', token);
  }

  public generateToken(request: any) {
    this.token = '';
    return this.http.post("http://localhost:8080/api/v1/auth/authenticate", request, { responseType: 'json' });



  }

  public getUserByToken(token: any) {
    this.token = token;

    return this.http.get("http://localhost:8080/api/v1/userbytoken", { responseType: 'json' });

  }
  public register(request: any) {

    this.token = '';
    return this.http.post("http://localhost:8080/api/v1/auth/register", request, { responseType: 'json' });

  }
  public isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
  public getToken() {
    return this.token;
  }
}
