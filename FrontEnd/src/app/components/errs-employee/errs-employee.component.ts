import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-errs-employee',
  templateUrl: './errs-employee.component.html',
  styleUrls: ['./errs-employee.component.css']
})
export class ErrsEmployeeComponent {

  userData: any;
  availablePoint!: number;
  phoneNumber!: number;
  userName!: String;
  earnedPoints!: number;
  email!: string;
  awardCount!: number;
  constructor(private userService: UserService) { }
  ngOnInit() {
    let resp = this.userService.getUser()
    resp.subscribe(data => {
      this.userData = data
      this.availablePoint = this.userData.points;
      this.userName = this.userData.name;
      this.email = this.userData.email;
      this.userName = this.userName.toUpperCase();
      this.phoneNumber = this.userData.phoneNumber;
      this.earnedPoints = this.userData.acquiredPoints;
    })
    this.userService.rewardCount().subscribe({
      next:(data:any)=>{
        this.awardCount=data.data;
      },
      error:(error:any)=>{
  
      }
      
    })

  }


}
