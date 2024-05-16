import { Component } from '@angular/core';
import { UserpointService } from 'src/app/service/user/userpoint.service';

@Component({
  selector: 'app-user-points',
  templateUrl: './user-points.component.html',
  styleUrls: ['./user-points.component.css']
})
export class UserPointsComponent {

  allocationData!: any;
  constructor(private pointService: UserpointService) { }
  ngOnInit() {
    let resp = this.pointService.allocationHistoryById()
    resp.subscribe(data => {
      this.allocationData = data
    })

  }

}



