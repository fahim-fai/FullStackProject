import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, TitleStrategy } from '@angular/router';
import { AdminproductService } from 'src/app/service/admin/adminproduct.service';
import { ImageService } from 'src/app/service/admin/image.service';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent {


  productId!: number | string;
  product!: any;
  Image!: string | null | undefined;
  isEditMode: boolean = false;
  productForm = this.formBuilder.group({
    name: ['', [Validators.required]],
    points: ['', [Validators.required]],
    category: ['', [Validators.required]],
    quantity: ['', [Validators.required]],
    termsAndCondition: ['', [Validators.required]],
    expiryDate: [''],
    image: ['',]
  })
  isAddProduct: boolean = false;

  constructor(private router: Router, private imageService: ImageService, private formBuilder: FormBuilder, private route: ActivatedRoute, private productService: AdminproductService, private snackbar: SnackbarService) { }

  ngOnInit() {
    // Get the product ID from the route parameters
    this.route.params.subscribe((params) => {
      this.productId = params['id'];

      if (this.productId === 'add') {
        this.isEditMode = true;
        this.isAddProduct = true;
        this.productForm = this.formBuilder.group({
          name: ['', [Validators.required]],
          points: ['', [Validators.required]],
          category: ['', [Validators.required]],
          quantity: ['', [Validators.required]],
          termsAndCondition: ['', [Validators.required]],
          expiryDate: ['',],
          image: ['']
        })

        return;
      }
      let resp = this.productService.productById(this.productId)
      resp.subscribe((data: any) => {
        this.product = data
        console.log(this.product)
        this.productId = this.product.id;
        this.Image = this.getImage();
        this.productForm = this.formBuilder.group({
          name: [this.product.name, [Validators.required]],
          points: [this.product.points, [Validators.required]],
          category: [this.product.category, [Validators.required]],
          quantity: [this.product.quantity, [Validators.required]],
          termsAndCondition: [this.product.termsAndCondition, [Validators.required]],
          expiryDate: [new Date(this.product.expiryDate).toISOString().slice(0, 10)],
          image: [this.Image,]
        })

      })
    });

  }

  toggleEditMode() {
    this.isEditMode = !this.isEditMode;
  }
  saveProduct() {
    // console.log(this.productForm.value);
    if (this.productForm.valid) {
      const data = this.productForm.value;
      data.image = this.Image;
      if (this.isAddProduct) {
        this.productService.addProduct(data);
        this.snackbar.openSnackBar("product added succesfully")
        this.router.navigate(['admindashboard/product'],);
        this.isEditMode = !this.isEditMode;
        return;
      }
      this.productService.updateProduct(data, this.productId)
      this.isEditMode = !this.isEditMode;
      this.snackbar.openSnackBar("product edited succesfully")
      this.router.navigate(['admindashboard/product'])

    }
    else {
      this.snackbar.openSnackBar("Enter all details")

    }
  }

  deleteProduct() {
    if (typeof this.productId !== 'number') return;
    this.productService.deleteProduct(this.productId);
    this.snackbar.openSnackBar("Deleted Successfully")
    this.router.navigate(['admindashboard/product'])

  }
  toHomePage() {
    this.router.navigate(['admindashboard']);
  }

  getImage() {
    return this.imageService.getImageById(this.product.image_id);
  }

  handleImageChange(e: any) {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      const res = reader.result;
      if (typeof res === 'string') {
        this.Image = res;
        // this.productForm.setValue({
        //   image: this.Image
        // });
      }
    }

    reader.readAsDataURL(file);
  }

}
