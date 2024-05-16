import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminproductService } from 'src/app/service/admin/adminproduct.service';
import { ImageService } from 'src/app/service/admin/image.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-deleted-products',
  templateUrl: './deleted-products.component.html',
  styleUrls: ['./deleted-products.component.css']
})
export class DeletedProductsComponent {
  productData: any;
  // images:any;


  constructor(private productService: AdminproductService, private snackbar: SnackbarService, private router: Router, private imageService: ImageService) {
    // this.images = {};
  }

  ngOnInit() {
    let resp = this.productService.products()
    resp.subscribe(data => {
      this.productData = data
    })

  }
  navigateToProductDetails(productId: number | string) {
    // Navigate to the product details page using the product ID
    this.router.navigate(['admindashboard/product', productId]);
  }

  getImage(id: number | null) {
    if (id === null) return '';
    // if(id in this.images) return this.images[id];

    return this.imageService.getImageById(id);

    // return '';

  }
  recoverProduct(id: number) {
    this.productService.recoverProduct(id)
    this.snackbar.openSnackBar("product recovered succsfully")
    this.router.navigate(['admindashboard/product'])
  }
}
