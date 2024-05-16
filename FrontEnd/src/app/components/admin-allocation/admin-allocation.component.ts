import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminallocationService } from 'src/app/service/admin/adminallocation.service';

@Component({
  selector: 'app-admin-allocation',
  templateUrl: './admin-allocation.component.html',
  styleUrls: ['./admin-allocation.component.css']
})
export class AdminAllocationComponent {
  allocationData: any;


  constructor(private allocationService: AdminallocationService, private router: Router) { }

  ngOnInit() {
    let resp = this.allocationService.allocationHistory()
    resp.subscribe(data => {
      this.allocationData = data

    })

  }
  navigateToAllocaionDetails(allocationId: number | string) {
    // Navigate to the allocation details page using the allocationId
    this.router.navigate(['admindashboard/allocation', allocationId]);
  }

}
