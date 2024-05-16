import { Component } from '@angular/core';
import { MatSnackBar, MatSnackBarContainer } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminproductService } from 'src/app/service/admin/adminproduct.service';
import { ImageService } from 'src/app/service/admin/image.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user/user.service';
import { UserproductService } from 'src/app/service/user/userproduct.service';

@Component({
  selector: 'app-user-products',
  templateUrl: './user-products.component.html',
  styleUrls: ['./user-products.component.css']
})
export class UserProductsComponent {

  productData: any;
  showDialog!: boolean;
  dialogMessage = '';
  productId!: number;
  currentPoints!: number;
  productPoints!: number;
  afterRedeem!: number;

  constructor(private productService: AdminproductService, private userService: UserService, private userProductService: UserproductService, private imageService: ImageService, private router: Router, private snackbar: SnackbarService) { }

  ngOnInit() {
    let resp = this.productService.products()
    resp.subscribe(data => {
      this.productData = data
      console.log(this.productData)
    })
    this.returnUserPoints();

  }


  onBuy(productId: number, productPoints: number) {
    this.productId = productId;
    this.productPoints = productPoints;
    this.showDialog = true;
    this.afterRedeem = this.currentPoints - productPoints;
    this.dialogMessage = 'After purchasing this product, your available points will be updated to ' + this.afterRedeem

    this.showDialog = true;

  }
  buyingSuccessful() {
    this.snackbar.openSnackBar("Product bought")
  }
  handleError(error: any): void {
    const errorMessage = error.error.error.errorMessage;
    if (errorMessage == "Cannot buy a product with quantity 0") {
      this.snackbar.openSnackBar("Not enough quantity")
    }
    if (errorMessage == "Not enough Points") {
      this.snackbar.openSnackBar("Not enough Points")

    }
  }
  getImage(id: number | null) {
    if (id === null) return '';
    // if(id in this.images) return this.images[id];

    return this.imageService.getImageById(id);

    // return '';
  }
  returnUserPoints() {
    this.userService.getUser().subscribe(
      //this.currentPoints
      (data: any) => {
        this.currentPoints = data.points
      }
    )

  }



  onDialogConfirmed() {
    const data = {
      productId: this.productId,

    }
    let resp = this.userProductService.buyProduct(data)
    resp.subscribe({
      next: (response: any) => {
        this.buyingSuccessful();
        this.ngOnInit();
        
      },
      error: (error: any) => {
        console.log(error)
        this.handleError(error)
      }
    }

    );
    this.showDialog = false;
  }

  onDialogCancelled() {
    this.snackbar.openSnackBar('purchasing Cancelled')
    this.showDialog = false;
  }

} 
