import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserproductService {

  baseUrl:String="http://localhost:8080/api/v1/";
  constructor(private http:HttpClient) { }
  public buyProduct(request:any){
    const  d = this.http.post(this.baseUrl+'user/redeem',request,);
 //   d.subscribe(data=>console.log(data));
    return d;
  }

}
