import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { EmployeedashboardComponent } from './components/employeedashboard/employeedashboard.component';
import { AdmindashboardComponent } from './components/admindashboard/admindashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/matirial.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AdminProductsComponent } from './components/admin-products/admin-products.component';
import { ErrsAdminComponent } from './components/errs-admin/errs-admin.component';
import { AdminRewardComponent } from './components/admin-reward/admin-reward.component';
import { AdminEmployeeComponent } from './components/admin-employee/admin-employee.component';
import { InterceptorInterceptor } from './service/interceptor.interceptor';
import { AdminAllocationComponent } from './components/admin-allocation/admin-allocation.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { RewardDetailComponent } from './components/reward-detail/reward-detail.component';
import { AllocationDetailsComponent } from './components/allocation-details/allocation-details.component';
import { UserPointsComponent } from './components/user/user-points/user-points.component';
import { RedeemHistoryComponent } from './components/user/redeem-history/redeem-history.component';
import { UserProductsComponent } from './components/user/user-products/user-products.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { AdminRedeemComponent } from './components/admin-redeem/admin-redeem.component';
import { PastEmployeesComponent } from './components/past-employees/past-employees.component';
import { DeletedProductsComponent } from './components/deleted-products/deleted-products.component';
import { UpdatePasswordComponent } from './components/user/update-password/update-password.component';
import { DialogueBoxComponent } from './components/user/dialogue-box/dialogue-box.component';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { ErrsEmployeeComponent } from './components/errs-employee/errs-employee.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ProductViewerComponent } from './components/user/product-viewer/product-viewer.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    EmployeedashboardComponent,
    AdmindashboardComponent,
    AdminProductsComponent,
    ErrsAdminComponent,
    AdminRewardComponent,
    AdminEmployeeComponent,
    AdminAllocationComponent,
    ProductDetailComponent,
    RewardDetailComponent,
    AllocationDetailsComponent,
    UserPointsComponent,
    RedeemHistoryComponent,
    UserProductsComponent,
    EmployeeDetailsComponent,
    AdminRedeemComponent,
    PastEmployeesComponent,
    DeletedProductsComponent,
    UpdatePasswordComponent,
    DialogueBoxComponent,
    ErrsEmployeeComponent,
    ProductViewerComponent,
    PagenotfoundComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatMenuModule,
    MatToolbarModule,
    MatTableModule,
    MatPaginatorModule,
    MatTooltipModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorInterceptor,
      multi: true
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
