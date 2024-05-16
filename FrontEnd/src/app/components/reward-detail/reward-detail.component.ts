import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminrewardsService } from 'src/app/service/admin/adminrewards.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-reward-detail',
  templateUrl: './reward-detail.component.html',
  styleUrls: ['./reward-detail.component.css']
})
export class RewardDetailComponent {

  rewardId!: number | string;
  reward!: any;
  isEditMode: boolean = false;
  rewardForm = this.formBuilder.group({
    name: ['', [Validators.required]],
    points: ['', [Validators.required]],

  })
  isAddreward: boolean = false;

  constructor(private router: Router, private formBuilder: FormBuilder, private route: ActivatedRoute, private rewardService: AdminrewardsService, private snackbar: SnackbarService) { }

  ngOnInit() {
    // Get the reward ID from the route parameters
    this.route.params.subscribe((params) => {
      this.rewardId = params['id'];
      // console.log(this.rewardId ==);
      if (this.rewardId === 'add') {
        this.isEditMode = true;

        this.isAddreward = true;
        this.rewardForm = this.formBuilder.group({
          name: ['', [Validators.required]],
          points: ['', [Validators.required]],

        })
        // console.log('RETURNING');
        return;
      }
      console.log(this.rewardId)
      let resp = this.rewardService.rewardById(this.rewardId)
      resp.subscribe((data: any) => {
        this.reward = data
        this.rewardId = this.reward.id;
        this.rewardForm = this.formBuilder.group({
          name: [this.reward.name, [Validators.required]],
          points: [this.reward.points, [Validators.required]],

        })
      })
    });
  }

  toggleEditMode() {
    this.isEditMode = !this.isEditMode;
  }
  saveReward() {
    if (this.rewardForm.valid) {
      if (this.isAddreward) {
        this.rewardService.addReward(this.rewardForm.value).subscribe({
          next:(response:any)=>{
            this.snackbar.openSnackBar("Added succesfully")
            this.router.navigate(['admindashboard/reward']);
            this.isEditMode = !this.isEditMode;
          },
          error:(error:any)=>{
            console.log(error)
            this.handleError(error);
          }
        })
      }
      this.rewardService.updateReward(this.rewardForm.value, this.rewardId).subscribe({
        next:(response:any)=>{
          this.isEditMode = !this.isEditMode;
          this.router.navigate(['admindashboard/reward']);
          this.snackbar.openSnackBar("Updated succesfully")
        },
        error:(error:any)=>{
          console.log(error);
          this.snackbar.openSnackBar("something went wrong")
        }
      })
      
    }
    else {
      this.snackbar.openSnackBar("Enter valid Details")
    }
  }
  handleError(error:any){
    if(error.error && error.error.errorMessage){
      this.snackbar.openSnackBar(error.errorMessage)
    }else{
      this.snackbar.openSnackBar("something went wrong")
    }
  }

  // deleteReward(){
  //   if( typeof this.rewardId !== 'number') return;
  //   this.rewardService.deleteReward(this.rewardId);
  //   this.snackbar.openSnackBar("Deleted Successfully")
  //   this.router.navigate(['admindashboard'])
  // }
  toHomePage() {
    this.router.navigate(['admindashboard']);
  }

}
