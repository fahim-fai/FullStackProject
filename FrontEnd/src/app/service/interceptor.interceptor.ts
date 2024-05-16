import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtClientService } from './auth.service';

@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {

  token!: String;

  constructor(private auth: JwtClientService) { }



  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    this.token = this.auth.getToken();
    if (this.token) {

      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + this.token
        }
      })
    }



    return next.handle(request);
  }
}
