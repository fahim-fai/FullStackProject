import { Component } from '@angular/core';
import { AdmindashboardService } from 'src/app/service/admin/admindashboard.service';

@Component({
  selector: 'app-errs-admin',
  templateUrl: './errs-admin.component.html',
  styleUrls: ['./errs-admin.component.css']
})
export class ErrsAdminComponent {
  displayedColumns: string[] = ['id', 'name', 'email', 'points'];
  userData: any;
  constructor(private adminService: AdmindashboardService) { }
  ngOnInit() {
    let resp = this.adminService.userPoints()
    resp.subscribe(data => {
      this.userData = data
    })

  }
}
