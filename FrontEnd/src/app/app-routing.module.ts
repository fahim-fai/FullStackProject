import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdmindashboardComponent } from './components/admindashboard/admindashboard.component';
import { EmployeedashboardComponent } from './components/employeedashboard/employeedashboard.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { RewardDetailComponent } from './components/reward-detail/reward-detail.component';
import { AllocationDetailsComponent } from './components/allocation-details/allocation-details.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { authGuard } from './service/auth.guard';
import { AdminRewardComponent } from './components/admin-reward/admin-reward.component';
import { AdminAllocationComponent } from './components/admin-allocation/admin-allocation.component';
import { AdminEmployeeComponent } from './components/admin-employee/admin-employee.component';
import { AdminProductsComponent } from './components/admin-products/admin-products.component';
import { AdminRedeemComponent } from './components/admin-redeem/admin-redeem.component';
import { ErrsAdminComponent } from './components/errs-admin/errs-admin.component';
import { UserPointsComponent } from './components/user/user-points/user-points.component';
import { UserProductsComponent } from './components/user/user-products/user-products.component';
import { RedeemHistoryComponent } from './components/user/redeem-history/redeem-history.component';
import { PastEmployeesComponent } from './components/past-employees/past-employees.component';
import { DeletedProductsComponent } from './components/deleted-products/deleted-products.component';
import { UpdatePasswordComponent } from './components/user/update-password/update-password.component';
import { ErrsEmployeeComponent } from './components/errs-employee/errs-employee.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'admindashboard', component: AdmindashboardComponent,canActivate: [authGuard],
    children: [
      { path: "errs", component: ErrsAdminComponent },
      { path: "reward", component: AdminRewardComponent},
      { path: "allocation", component: AdminAllocationComponent },
      { path: "employee", component: AdminEmployeeComponent},
      { path: "pastemployee", component: PastEmployeesComponent},
      { path: "product", component: AdminProductsComponent },
      { path: "deletedproducts", component: DeletedProductsComponent },
      { path: "redeem", component: AdminRedeemComponent},
      { path: "product/:id", component: ProductDetailComponent },
      { path: 'reward/:id', component: RewardDetailComponent },
      { path: 'allocation/:id', component: AllocationDetailsComponent},
      { path: 'employee/:id', component: EmployeeDetailsComponent }
    ],
    
  },
  {
    path: 'employeedashboard', component: EmployeedashboardComponent,canActivate: [authGuard],
    children: [
      { path: "errs", component: ErrsEmployeeComponent },
      { path: "points", component: UserPointsComponent },
      { path: "updatepassword", component: UpdatePasswordComponent },
      { path: "product", component: UserProductsComponent },
      { path: "redeem", component: RedeemHistoryComponent }
    ],
      
  },
  {path:"**",component:PagenotfoundComponent}
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
