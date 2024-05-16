import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminredeemService {
    baseUrl: String = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) { }
  public redeemHistory() {
    return this.http.get(this.baseUrl + "admin/redeemHistory", { responseType: 'json' });
  }
}
