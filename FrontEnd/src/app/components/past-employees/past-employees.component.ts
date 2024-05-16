import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminemployeeService } from 'src/app/service/admin/adminemployee.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-past-employees',
  templateUrl: './past-employees.component.html',
  styleUrls: ['./past-employees.component.css']
})
export class PastEmployeesComponent {
  userData: any;
  constructor(private snackbar: SnackbarService, private employeeService: AdminemployeeService, private router: Router) { }
  ngOnInit() {
    let resp = this.employeeService.inActiveUsers()
    resp.subscribe(data => {
      this.userData = data
    })

  }
  deletePastUser(id: number) {
    this.employeeService.deletePastUser(id)
    this.snackbar.openSnackBar("Deleted succesfully")
    this.router.navigate(['admindashboard/employee'])
  }


}
