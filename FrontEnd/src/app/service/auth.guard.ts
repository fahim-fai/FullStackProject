import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtClientService } from './auth.service';

export function authGuard() {
  const authService: JwtClientService = inject(JwtClientService);
  const router = inject(Router)
  if (authService.isLoggedIn()) {
    return true;
  } else {
    router.navigate(['']);
    return false;
  }
}
 
    
 
