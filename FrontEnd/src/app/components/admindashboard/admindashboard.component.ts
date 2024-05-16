import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent {
  constructor(private router: Router) { }
  ngOnInit() {
    this.loadAdminDashboard()
  }
  dashboardNumber: number = 0;
  dashboard(dashboardNumber: number) {
    this.dashboardNumber = dashboardNumber;
  }
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
  loadAdminDashboard() {
    this.router.navigate(['admindashboard/errs']);
  }
  navigateToEmployeeDashboard() {
    this.router.navigate(['employeedashboard']);
  }
  navigateToAllocaionDetails(allocationId: number | string) {
    // Navigate to the allocation details page using the allocationId
    this.router.navigate(['/allocation', allocationId]);
  }

}
