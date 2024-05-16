import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtClientService } from 'src/app/service/auth.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent {
  email!: string;

  changePasswordForm = this.formBuilder.group({

    email: ['', Validators.required],
    password: ['', [Validators.required]],
    confirmPassword: ['', [Validators.required]]

  });
  constructor(private formBuilder: FormBuilder, private snackbar: SnackbarService, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUser().subscribe((data: any) => {
      // console.log(data.email)
      // this.email=data.email;
      this.changePasswordForm = this.formBuilder.group({

        email: [data.email, Validators.required],
        password: ['', [Validators.required]],
        confirmPassword: ['', [Validators.required]]

      })
    }



    )

  }

  onSubmit() {

    if (this.changePasswordForm.valid) {
      if (this.changePasswordForm.get('password')?.value == this.changePasswordForm.get('confirmPassword')?.value) {

        console.log({ passsword: this.changePasswordForm.get('password')?.value })
        this.userService.changePassword({ password: this.changePasswordForm.get('password')?.value }).subscribe({
          next: (data: any) => {
            this.snackbar.openSnackBar(data.data)
            this.router.navigate(['employeedashboard'])
          },
          error: (error: any) => {
            this.snackbar.openSnackBar(error.error.error.errorMessage)
          }
        }

        )

      }
      else {
        this.snackbar.openSnackBar("Password does not match")
      }
    }
    else {
      this.snackbar.openSnackBar("Enter valid Details")
    }
  }
}
