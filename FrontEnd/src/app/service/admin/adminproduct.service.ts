import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminproductService {

  baseUrl: String = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) { }

  public products() {
    return this.http.get(this.baseUrl + "admin/products", { responseType: 'json' });
  }

  public productById(id: any) {
    return this.http.get(this.baseUrl + "admin/product/id/" + id, { responseType: 'json' });
  }

  public updateProduct(request: any, id: any) {
    console.log(id);
    const d = this.http.put(this.baseUrl + "admin/updateProduct/id/" + id, request, { responseType: 'json' });
    console.log(d);
    d.subscribe(data => console.log(data));
    return d;
  }
  public recoverProduct(id: any) {
    console.log(id)
    return this.http.put(this.baseUrl + "admin/recoverProduct/" + id, { responseType: 'json' }).subscribe(data => data);

  }

  public deleteProduct(id: number) {
    const d = this.http.delete(this.baseUrl + "admin/deleteProduct/id/" + id, { responseType: 'json' });
    d.subscribe(data => console.log(data));
    return d;
  }

  public addProduct(request: any) {
    const d = this.http.post(this.baseUrl + 'admin/product', request, { responseType: 'json' });
    d.subscribe(data => console.log(data));
    return d;
  }
  // uploadProductWithImage(productWithImageDto: any): Observable<any> {
  //   return this.http.post(this.baseUrl+'admin/upload', productWithImageDto);
  // }

}
