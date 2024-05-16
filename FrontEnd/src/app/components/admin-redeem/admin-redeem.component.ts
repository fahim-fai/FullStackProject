import { Component } from '@angular/core';
import { AdminredeemService } from 'src/app/service/admin/adminredeem.service';

@Component({
  selector: 'app-admin-redeem',
  templateUrl: './admin-redeem.component.html',
  styleUrls: ['./admin-redeem.component.css']
})
export class AdminRedeemComponent {

  redeemData!: any;
  constructor(private redeeemService: AdminredeemService) { }
  ngOnInit() {
    let resp = this.redeeemService.redeemHistory()
    resp.subscribe(data => {
      this.redeemData = data
    })

  }

}
