import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AdminproductService } from 'src/app/service/admin/adminproduct.service';
import { ImageService } from 'src/app/service/admin/image.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserProductsComponent } from '../user-products/user-products.component';
import { UserproductService } from 'src/app/service/user/userproduct.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-viewer',
  templateUrl: './product-viewer.component.html',
  styleUrls: ['./product-viewer.component.css']
})
export class ProductViewerComponent {
  @Input() productId!: number;
  @Output() confirmed = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();


  constructor(private service: AdminproductService, private imageService: ImageService, private snackbar: SnackbarService, private userProductService: UserproductService, private router: Router) { }

  showDialog!: boolean;
  productData!: any;
  Image!: string | null;
  points!: number;
  ngOnInit() {
    let resp = this.service.productById(this.productId)
    resp.subscribe((data: any) => {

      this.productData = data
      this.points = this.productData.points;
      this.Image = this.getImage();
      console.log(this.Image)
    })

  }
  getImage() {
    return this.imageService.getImageById(this.productData.image_id);
  }

  onConfirm() {
    this.showDialog = true;

  }

  onCancel() {
    this.cancelled.emit();
  }
  onDialogCancelled() {
    this.cancelled.emit();
  }
  onDialogConfirmed() {
    this.confirmed.emit();
    this.showDialog = false;
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
}
