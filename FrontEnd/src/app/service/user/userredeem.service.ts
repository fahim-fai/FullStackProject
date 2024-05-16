import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserredeemService {

  baseUrl:String="http://localhost:8080/api/v1/";

  constructor(private http:HttpClient) { }
  public redeemHistoryById(){
    return this.http.get(this.baseUrl+"user/redeem",{responseType:'json'});
  }
}
