import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdmindashboardService {


  constructor(private http: HttpClient) { }

  public userPoints() {

    // this.intreceptor.setToken(token)


    // console.log("http://localhost:8080/api/v1/demo-controller/givingresponse)
    return this.http.get("http://localhost:8080/api/v1/admin/userPoints", { responseType: 'json' });

  }
}
