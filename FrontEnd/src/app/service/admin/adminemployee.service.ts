import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SnackbarService } from '../snackbar.service';

@Injectable({
  providedIn: 'root'
})
export class AdminemployeeService {
  baseUrl: String = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient, private snackbar: SnackbarService) { }

  public users() {

    return this.http.get("http://localhost:8080/api/v1/admin/activeUsers", { responseType: 'json' });

  }
  public inActiveUsers() {

    return this.http.get("http://localhost:8080/api/v1/admin/inActiveUsers", { responseType: 'json' });

  }

  public userPoints() {

    return this.http.get("http://localhost:8080/api/v1/admin/userPoints", { responseType: 'json' });

  }
  public getUserById(id: number) {
    return this.http.get(this.baseUrl + "admin/user/" + id, { responseType: 'json' });
  }
  public deleteUserById(id: any) {
    const d = this.http.delete(this.baseUrl + "admin/user/" + id, { responseType: 'json' });
    d.subscribe(data => console.log(data));
    return d;
  }
  public deletePastUser(id: any) {
    return this.http.delete(this.baseUrl + "admin/pastuser/" + id, { responseType: 'json' }).subscribe(data => console.log(data));

  }

}
