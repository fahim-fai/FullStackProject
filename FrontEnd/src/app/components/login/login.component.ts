import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtClientService } from 'src/app/service/auth.service';
import { Router } from '@angular/router'
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authRequest: any;

  response: any;


  email!: String;
  password!: String;
  login!: FormGroup;
  loginEmail!: String;

  constructor(private formBuilder: FormBuilder, private router: Router, private service: JwtClientService, private snackbar: SnackbarService) {
  }



  ngOnInit(): void {
    localStorage.clear();
    this.login = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]


    })
  }


  onSubmit() {
    if (this.login.valid) {
      this.loginEmail = this.login.get('email')?.value;
      this.service.setEmail(this.loginEmail);
      this.getAccessToken(this.login.value);
    }
    else {
      this.snackbar.openSnackBar("Enter Valid Email and Password")
    }

  }

  public getAccessToken(authRequest: any) {
    let resp = this.service.generateToken(authRequest);
    resp.subscribe({
      next: (data: any) => {

        this.accessApi(data.data)
        const token = data.data['token'];
        this.service.setToken(token);
        console.log(token)
        localStorage.setItem('token', token);
      },
      error: (error: any) => {
        console.log(error)
        this.handleError(error);
      }
    }

    );
  }

  private handleError(error: any): void {
    if (error.error.error) {
      const errorMessage = error.error.error.errorMessage;
      if (errorMessage == "No Employee with given Email") {
        this.snackbar.openSnackBar(errorMessage)
      }
      else if (errorMessage == "Employee Already Deleted") {
        this.snackbar.openSnackBar(errorMessage)
      }
      else if (errorMessage == "Authentication failed") {
        this.snackbar.openSnackBar(errorMessage)
      }
    }

    else {
      this.snackbar.openSnackBar("Something went wrong")
    }





  }

  public accessApi(tokenjson: any) {


    const token = tokenjson['token'];
    let resp = this.service.getUserByToken(token);

    resp.subscribe(data => {
      this.response = data
      this.service.setUserDetails(this.response);
      localStorage.setItem('email', this.response.email);
      localStorage.setItem('id', this.response.id);
      localStorage.setItem('user', this.response);
      localStorage.setItem('role', this.response.role);
      if (this.response.role == 'USER') {
        this.router.navigate(['/employeedashboard/'])
        this.snackbar.openSnackBar("Login succesful")

      }
      else if (this.response.role == 'ADMIN') {
        this.router.navigate(['/employeedashboard/'])
        this.snackbar.openSnackBar("Login succesful")
      }


    });


  }
  navigateToRegister() {
    this.router.navigate(['/register'])
  }


}
