import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminallocationService } from 'src/app/service/admin/adminallocation.service';
import { AdminrewardsService } from 'src/app/service/admin/adminrewards.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-allocation-details',
  templateUrl: './allocation-details.component.html',
  styleUrls: ['./allocation-details.component.css']
})
export class AllocationDetailsComponent {

  allocationId!: number | string;
  userName!: String;
  rewardName!: String;
  awardedPoints!: any;
  allocation!: any;
  rewards!: any;
  users!: any;
  allocationForm!: FormGroup;
  isAddallocation: boolean = false;
  selectedEmails!: any;
  dropdownSettings: any;

  constructor(private router: Router, private formBuilder: FormBuilder, private route: ActivatedRoute, private allocationService: AdminallocationService, private rewardService: AdminrewardsService, private snackbar: SnackbarService) { }

  ngOnInit() {
    // Get the allocation ID from the route parameters
    this.route.params.subscribe((params) => {
      this.allocationId = params['id'];
      if (this.allocationId === 'add') {
        this.isAddallocation = true;
        this.allocationService.getUsers().subscribe((data: any) => {
          this.users = data;
        });

        let resp = this.rewardService.rewards()
        resp.subscribe(data => {
          this.rewards = data
        })

        this.isAddallocation = true;
        this.allocationForm = this.formBuilder.group({
          rewardId: ['', [Validators.required]],
          userIds: ['', [Validators.required]],

        })

        // console.log('RETURNING');
        return;
      }
      console.log(this.allocationId)
      let resp = this.allocationService.allocationById(this.allocationId)
      resp.subscribe((data: any) => {
        this.allocation = data
        this.allocationId = this.allocation.id;

        this.userName = this.allocation.userName;
        this.rewardName = this.allocation.recognitionActivityName;
        this.awardedPoints = this.allocation.awardedpoints;

      })
    });

  }


  saveallocation() {
    if (this.allocationForm.valid) {
      const rewardIdAsNumber: number = this.allocationForm.get('rewardId')!.value;
      const userIdsAsNumbers: number[] = this.allocationForm.get('userIds')!.value;
      const request: { [key: number]: number[] } = {
        [rewardIdAsNumber]: userIdsAsNumbers
      }
      this.allocationService.addAllocation(request)
      this.snackbar.openSnackBar("Point allocated succesfully")
      this.router.navigate(['admindashboard/allocation']);

      return;


    }
    else {
      this.snackbar.openSnackBar("Enter Valid Details")
    }

  }

  deleteallocation() {

    if (typeof this.allocationId !== 'number') return;
    this.allocationService.deleteAllocation(this.allocationId);
    this.snackbar.openSnackBar("Deleted Successfully")
    this.router.navigate(['admindashboard'])
  }
  toHomePage() {
    this.router.navigate(['admindashboard']);
  }

}
