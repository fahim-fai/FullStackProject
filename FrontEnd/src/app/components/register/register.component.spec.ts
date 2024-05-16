import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { JwtClientService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user/user.service';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { of, throwError } from 'rxjs';

// describe('RegisterComponent', () => {
//   let component: RegisterComponent;
//   let fixture: ComponentFixture<RegisterComponent>;

//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [ RegisterComponent ]
//     })
//     .compileComponents();

//     fixture = TestBed.createComponent(RegisterComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });
let component:RegisterComponent;
let fixture:ComponentFixture<RegisterComponent>;
let authServiceSpy:jasmine.SpyObj<JwtClientService>;
let routerSpy:jasmine.SpyObj<Router>;
let snackbarServiceSpy: jasmine.SpyObj<SnackbarService>;
// let formBuilderSpy:jasmine.SpyObj<FormBuilder>;
let registerForm: jasmine.SpyObj<FormGroup>;
//let asd:FormBuilder
let formBuilder:FormBuilder
let beforeEachFunction=async()=>{
  authServiceSpy=jasmine.createSpyObj('JwtClientService',['register']);
  snackbarServiceSpy=jasmine.createSpyObj('SnackbarService',['openSnackBar']);
  // formBuilderSpy=jasmine.createSpyObj('FormBuilder',['group']);
  registerForm = jasmine.createSpyObj('FormGroup', ['valid']);
 
  await TestBed.configureTestingModule({
    declarations:[RegisterComponent],
    providers:[
      {provide:JwtClientService,useValue:authServiceSpy},
      {provide:SnackbarService,useValue:snackbarServiceSpy},
      // {provide:FormBuilder,useValue:formBuilderSpy},
      {provide:FormGroup,useValue:registerForm},
      FormBuilder 
    ],
    imports:[
      RouterTestingModule,
      ReactiveFormsModule,
      MatSnackBarModule,
      ReactiveFormsModule 
      
    ]
  })
  .compileComponents();
  fixture= TestBed.createComponent(RegisterComponent);
  component=fixture.componentInstance;

  
  authServiceSpy=TestBed.inject(JwtClientService) as jasmine.SpyObj<JwtClientService>
  routerSpy=TestBed.inject(Router) as jasmine.SpyObj<Router>;
  snackbarServiceSpy=TestBed.inject(SnackbarService) as jasmine.SpyObj<SnackbarService>;
  formBuilder = TestBed.inject(FormBuilder); // Inject FormBuilder

}
describe('navigateToLogin',()=>{
  beforeEach(async()=>{
    await beforeEachFunction();
  })
  it('should navigate to login when navigateToLogin is called',()=> {
    const navigateSpy = spyOn(routerSpy, 'navigate');
    component.navigateToLogin();
    expect(navigateSpy).toHaveBeenCalledWith(['']);
  })
})
describe('handleError',()=>{
  beforeEach(async()=>{
    await beforeEachFunction();
  })
  it('should handle error and show snackbar message when an error occurs during registration',()=> {
    const errorObject:any = {
      error: {
        error: {
          errorMessage: 'This is error message'
        }
      }
    };

    const errorMessage = errorObject.error.error.errorMessage;
    component.handleError(errorObject);
  //  const snackBarSpy = spyOn(snackbarServiceSpy, 'openSnackBar');
    expect(snackbarServiceSpy.openSnackBar).toHaveBeenCalledWith(errorMessage);
  })

  it('failure test',()=>{
    const errorObj:any={
      error:"hai"
    }
    //const errorMessage = errorObject.error.error.errorMessage;
    component.handleError(errorObj);
    expect(snackbarServiceSpy.openSnackBar).toHaveBeenCalledWith("Something went wrong");
    })
})
// describe('ngOnInit',()=>{
//   beforeEach(async()=>{
//     await beforeEachFunction();
//   })
//   it('is ngOnInit working',()=>{
//     component.ngOnInit()
//    // expect(formBuilderSpy.group).toHaveBeenCalled()
//   })
// })
describe('onSubmit',()=>{
  beforeEach(async()=>{
    await beforeEachFunction();
    
  })
  it('User registering succcessfully',()=>{
  
  //  registerForm = formBuilderSpy.group({
  //   name: ['', [Validators.required]],
  //   email: ['', [Validators.required]],
  //   password: ['', [Validators.required]],
  //   confirmPassword: ['', [Validators.required]],
  //   phoneNumber: ['', [Validators.required]],
    
  // })
  //   component.registerForm={
  //     name: ['Test'],
  //     email: ['test'],
  //     password: ['password'],
  //     confirmPassword: ['password'],
  //     phoneNumber: ['1234567890'],
  //     vaild:true
  //   } as FormGroup;
    //registerForm.valid 
    const mockFormValues = {
      name: 'John Doe',
      email: 'john@example.com',
      password: 'password',
      confirmPassword: 'password',
      phoneNumber: '1234567890'
    };

    // Create a FormGroup with mock values
    const navigateSpy = spyOn(routerSpy, 'navigate');
    const mockRegisterForm: FormGroup = formBuilder.group(mockFormValues);
    component.registerForm= mockRegisterForm
    authServiceSpy.register.and.returnValue(of({}));
    component.onSubmit()
    expect(snackbarServiceSpy.openSnackBar).toHaveBeenCalledWith('User registered succesfully');
    expect(navigateSpy).toHaveBeenCalledWith(['']);
  })
  it('Password does not match',()=>{
  

      const mockFormValues = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'password',
        confirmPassword: 'password123',
        phoneNumber: '1234567890'
      };
  

      const mockRegisterForm: FormGroup = formBuilder.group(mockFormValues);
      component.registerForm= mockRegisterForm
      authServiceSpy.register.and.returnValue(of({}));
      component.onSubmit()
      expect(snackbarServiceSpy.openSnackBar).toHaveBeenCalledWith('Password does not match');
     
    })
})
