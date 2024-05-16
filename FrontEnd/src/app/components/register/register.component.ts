import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { JwtClientService } from 'src/app/service/auth.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  email!: String;
  name!: String;
  password!: String;
  confirmPassword!: String;
  phoneNumber!: String;
  registerForm!: FormGroup;
  response: any;
  isFormValid: boolean = false;

  constructor(private formBuilder: FormBuilder, private snackbar: SnackbarService, private router: Router, private service: JwtClientService) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
      phoneNumber: ['', Validators.required]
    });
  
  }

  onSubmit() {
    if (this.registerForm.valid) {
      if (this.registerForm.get('password')?.value == this.registerForm.get('confirmPassword')?.value) {
        this.service.register(this.registerForm.value).subscribe({
          next: (response: any) => {
            this.snackbar.openSnackBar("User registered succesfully")
            this.router.navigate([''])
          },
          error: (error: any) => {
            this.handleError(error)
          }
        }
        );
      }
      else {
        this.snackbar.openSnackBar("Password does not match")
      }
    }
    else {
      this.snackbar.openSnackBar("Enter valid Details")
    }
  }
  navigateToLogin() {
    this.router.navigate([''])
  }
  handleError(error: any): void {
    if(error && error.error && error.error.error && error.error.error.errorMessage){
      const errorMessage = error.error.error.errorMessage;
      this.snackbar.openSnackBar(errorMessage)
    }else{
      this.snackbar.openSnackBar("Something went wrong")
    }
    
  }

}
