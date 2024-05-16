import { Component } from '@angular/core';
import { UserredeemService } from 'src/app/service/user/userredeem.service';

@Component({
  selector: 'app-redeem-history',
  templateUrl: './redeem-history.component.html',
  styleUrls: ['./redeem-history.component.css']
})
export class RedeemHistoryComponent {
  redeemData!: any;
  constructor(private redeeemService: UserredeemService) { }
  ngOnInit() {
    let resp = this.redeeemService.redeemHistoryById()
    resp.subscribe(data => {
      this.redeemData = data
    })

  }

}
