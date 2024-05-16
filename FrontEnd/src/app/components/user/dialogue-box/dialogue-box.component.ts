import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AdminemployeeService } from 'src/app/service/admin/adminemployee.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-dialogue-box',
  templateUrl: './dialogue-box.component.html',
  styleUrls: ['./dialogue-box.component.css']
})
export class DialogueBoxComponent {
  @Input() points!: number;
  @Output() confirmed = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();
  message: String = '';
  newPoints!: number;
  constructor(private service: UserService, private snackbar: SnackbarService, private router: Router) { }
  availablePoints!: number;
  ngOnInit() {
    this.service.getUser().subscribe((data: any) => {
      this.availablePoints = data.points
      this.newPoints = this.availablePoints - this.points;
      if (this.newPoints < 0) {
        this.snackbar.openSnackBar("Not enough Points")
        this.onCancel();
      } else {
        this.message = "After Buying the product your Points will be " + this.newPoints + "  Are you sure you want to buy?"
      }
    })
    // this.availablePoints=localStorage.getItem('')
  }

  onConfirm() {
    this.confirmed.emit();

  }

  onCancel() {
    this.cancelled.emit();
  }

}
