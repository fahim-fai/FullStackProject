import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminrewardsService {

  baseUrl: String = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) { }

  public rewards() {
    return this.http.get(this.baseUrl + "admin/recognitionActivties", { responseType: 'json' });
  }

  public rewardById(id: number | string) {
    return this.http.get(this.baseUrl + "admin/recognitionActivty/id/" + id, { responseType: 'json' });
  }
  public updateReward(request: any, id: any) {
    return this.http.put(this.baseUrl + "admin/updateRecognitionActivity/id/" + id, request, { responseType: 'json' });
    
  }

  public deleteReward(id: number) {
    return this.http.delete(this.baseUrl + "admin/deleteRecognitionActivity/id/" + id, { responseType: 'json' }).subscribe(data => console.log(data));
    // d.subscribe( data => console.log(data));
    // return d;
  }

  public addReward(request: any) {
    return  this.http.post(this.baseUrl + 'admin/addRecognitionActivity', request, { responseType: 'json' });
    
  }

}
