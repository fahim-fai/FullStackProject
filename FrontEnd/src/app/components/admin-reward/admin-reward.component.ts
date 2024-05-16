import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminrewardsService } from 'src/app/service/admin/adminrewards.service';

@Component({
  selector: 'app-admin-reward',
  templateUrl: './admin-reward.component.html',
  styleUrls: ['./admin-reward.component.css']
})
export class AdminRewardComponent {
  rewardData: any;


  constructor(private router: Router, private rewardService: AdminrewardsService) { }

  ngOnInit() {
    let resp = this.rewardService.rewards()
    resp.subscribe(data => {
      this.rewardData = data
    })

  }

  navigateToRewardtDetails(rewardId: number | string) {
    // Navigate to the product details page using the product ID
    this.router.navigate(['admindashboard/reward', rewardId]);
  }

}
