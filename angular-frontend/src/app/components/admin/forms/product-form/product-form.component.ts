import {Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../../../services/product.service";
import {Stock} from "../../../../common/prod-details/stock";
import {StockService} from "../../../../services/prod-details/stock.service";
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {ImageService} from "../../../../services/images/image.service";
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.sass']
})
export class ProductFormComponent implements OnInit {

  productForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required),
    sex: new FormControl('unisex', Validators.required),
    description: new FormControl('', Validators.required),
    image: new FormControl('', Validators.required)
  });

  product = new ProductInfo();
  imageFile: File | null = null;
  subCategoryList: SubCategory[] = [];
  errorMessage: string | undefined;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private subCategoryService: SubCategoryService,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    const hasSubCategoryId: boolean = this.route.snapshot.paramMap.has('subCatId');
    this.route.params.subscribe(() => {
      this.initProductForm(hasSubCategoryId);
    })

    const hasProductId: boolean = this.route.snapshot.paramMap.has('id');
    this.route.params.subscribe(() => {
      this.getProductData(hasProductId);
    })
  }


  getProductData(hasProductId: boolean) {
    if (hasProductId) {
      const productId: number = +this.route.snapshot.paramMap.get('id')!;
      this.productService.getProductByIdAdmin(productId).subscribe(
        data => {
          this.product = data;
        }
      )
    }
  }

  initProductForm(hasSubCategoryId: boolean) {
    if (hasSubCategoryId) {
      const subCategory: number = +this.route.snapshot.paramMap.get('subCatId')!;
      this.subCategoryService.getSubCategoryById(subCategory).subscribe(
        data => {
          this.product.subCategory = data;
        }
      )
    } else {
      const productId: number = +this.route.snapshot.paramMap.get('id')!;
      this.productService.getProductByIdAdmin(productId).subscribe(
        data => {
          this.product = data;
        }
      )
    }

    this.subCategoryService.getAllSubCategories().subscribe(
      data => {
        this.subCategoryList = data;
      }
    )
  }

  saveProduct() {
    if (this.productForm.valid) {
      const product: ProductInfo = this.productForm.value;
      this.productService.saveProduct(this.product, this.imageFile)
        .subscribe(
          response => {
            // Handle success response
            console.log('Product saved successfully', response);
          },
          error => {
            // Handle error response
            console.error('Error saving product', error);
            this.errorMessage = error.error.message;
          }
        );
    } else {
      // Form is invalid, mark fields as touched to display validation errors
      this.productForm.markAllAsTouched();
    }
  }

  onImageSelected(event: any) {
    this.imageFile = event.target.files[0];
  }

  get name() {return this.productForm.get('name'); }
  get sex() { return this.productForm.get('sex'); }
  get price() { return this.productForm.get('price'); }
  get description() { return this.productForm.get('description'); }
  get image() {return this.productForm.get('image'); }
}

