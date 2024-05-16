import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  baseURL:string="http://localhost:8080/api/v1/";

  public getUser() {
    return this.http.get("http://localhost:8080/api/v1/userbytoken", { responseType: 'json' });
  }
  public changePassword(request: any) {
    return this.http.put("http://localhost:8080/api/v1/user/changepassword", request, { responseType: 'json' });
  }
  public rewardCount(){
    return this.http.get(this.baseURL+"user/rewardCount",{responseType:'json'});
  }

}
