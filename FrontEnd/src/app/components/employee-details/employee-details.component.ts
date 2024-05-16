import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminemployeeService } from 'src/app/service/admin/adminemployee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent {
  userId!: number;
  user!: any;
  name!: any;
  email!: any;
  role!: any;



  constructor(private router: Router, private route: ActivatedRoute, private userService: AdminemployeeService) { }

  ngOnInit() {
    // Get the user ID from the route parameters
    this.route.params.subscribe((params) => {
      this.userId = params['id'];
      let resp = this.userService.getUserById(this.userId)
      resp.subscribe((data: any) => {
        this.user = data
        this.name = this.user.name;
        this.userId = this.user.id;
        this.email = this.user.email;
        this.role = this.user.role;

      })
    });

  }



  deleteuser() {
    this.userService.deleteUserById(this.userId);
    this.router.navigate(['admindashboard'])
  }
  toHomePage() {
    this.router.navigate(['admindashboard']);
  }

}
