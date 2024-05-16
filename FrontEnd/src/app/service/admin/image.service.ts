import { HttpClient, HttpSentEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  baseUrl: String = "http://localhost:8080/api/v1/";
  images: { [id: number]: string };

  constructor(private http: HttpClient) {
    this.images = {};
  }

  public getImageById(id: number) {

    if (id in this.images) return this.images[id];


    this.http.get(this.baseUrl + "images/" + id, { responseType: 'json' }).pipe(
      map((data: any) => data.image)
    ).subscribe(img => {
      this.images[id] = img;
      // image = img;
    })

    return null;

  }

}
