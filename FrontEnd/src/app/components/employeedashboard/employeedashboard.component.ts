import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-employeedashboard',
  templateUrl: './employeedashboard.component.html',
  styleUrls: ['./employeedashboard.component.css']
})
export class EmployeedashboardComponent {

  constructor(private userService: UserService, private router: Router) { }
  isAdmin: boolean = false;
  userData: any;
  userPoint!: number;
  dashboardNumber: number = 1;
  userName!: String;
  earnedPoints!: number;
  awardCount!:number;

  ngOnInit() {
    this.loadEmployeeDashboard();
    let resp = this.userService.getUser()
    resp.subscribe(data => {
      this.userData = data
      this.userPoint = this.userData.points;
      this.userName = this.userData.name;
      this.earnedPoints = this.userData.acquiredPoints;
    })
    if (localStorage.getItem('role') == 'ADMIN') {
      this.isAdmin = true;
    }
    
    


  }
  dashboard(dashboardNumber: number) {
    this.dashboardNumber = dashboardNumber;
  }
  loadEmployeeDashboard() {
    this.router.navigate(['employeedashboard/errs']);
  }
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
  navigateToAdminPage() {
    this.router.navigate(['admindashboard']);
  }

}
