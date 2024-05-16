import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminallocationService {

  baseUrl: String = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) { }

  public allocationHistory() {

    return this.http.get(this.baseUrl + "admin/allocationHistory", { responseType: 'json' });

  }
  public allocationById(id: number | string) {
    return this.http.get(this.baseUrl + "admin/allocationHistory/" + id, { responseType: 'json' });
  }


  public deleteAllocation(id: number) {
    const d = this.http.delete(this.baseUrl + "admin/allocationHistory/" + id, { responseType: 'json' });
    d.subscribe(data => console.log(data));
    return d;
  }

  public addAllocation(request: any) {
    const d = this.http.post(this.baseUrl + 'admin/reward', request, { responseType: 'json' });
    d.subscribe(data => console.log(data));
    return d;
  }
  public getUsers() {

    return this.http.get(this.baseUrl + "admin/activeUsers", { responseType: 'json' });

  }


}
