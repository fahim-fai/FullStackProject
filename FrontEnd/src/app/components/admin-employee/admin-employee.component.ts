import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AdminemployeeService } from 'src/app/service/admin/adminemployee.service';

@Component({
  selector: 'app-admin-employee',
  templateUrl: './admin-employee.component.html',
  styleUrls: ['./admin-employee.component.css']
})
export class AdminEmployeeComponent {

  userData: any;
  constructor(private employeeService: AdminemployeeService, private router: Router) { }
  ngOnInit() {
    let resp = this.employeeService.users()
    resp.subscribe(data => {
      this.userData = data
    })

  }
  navigateToEmployeeDetails(allocationId: number | string) {
    // Navigate to the allocation details page using the allocationId
    this.router.navigate(['admindashboard/employee', allocationId]);
  }

}
