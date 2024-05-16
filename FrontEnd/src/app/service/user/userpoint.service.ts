import { HttpClient, HttpSentEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserpointService {

  baseUrl:String="http://localhost:8080/api/v1/";

  constructor(private http:HttpClient) { }
  public allocationHistoryById(){
    return this.http.get(this.baseUrl+"user/allocationHistory",{responseType:'json'});
  }
}
