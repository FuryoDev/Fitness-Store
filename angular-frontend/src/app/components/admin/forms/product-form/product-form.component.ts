import { Component, OnInit } from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../../../services/product.service";
import {Stock} from "../../../../common/prod-details/stock";
import {StockService} from "../../../../services/prod-details/stock.service";
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {ImageService} from "../../../../services/images/image.service";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.sass']
})
export class ProductFormComponent implements OnInit {

  product = new ProductInfo();
  imageFile: File | null = null;
  imageFolderPath: string = "assets/images/products/"

  subCategoryList: SubCategory[] = [];

  constructor(private route: ActivatedRoute,
              private subCategoryService: SubCategoryService,
              private productService: ProductService) { }

  ngOnInit(): void {
    const hasSubCategoryId: boolean = this.route.snapshot.paramMap.has('subCatId');
    this.route.params.subscribe(() =>  {
      this.initProductForm(hasSubCategoryId);
    })

    const hasProductId: boolean = this.route.snapshot.paramMap.has('id');
    this.route.params.subscribe(() => {
      this.getProductData(hasProductId);
    })
  }


  getProductData(hasProductId: boolean) {
    if(hasProductId) {
      const productId: number = +this.route.snapshot.paramMap.get('id')!;
      this.productService.getProductByIdAdmin(productId).subscribe(
        data => {
          this.product = data;
        }
      )
    }
  }
  initProductForm(hasSubCategoryId: boolean) {
    if(hasSubCategoryId) {
      const subCategory: number = +this.route.snapshot.paramMap.get('subCatId')!;
      this.subCategoryService.getSubCategoryById(subCategory).subscribe(
        data => {
          this.product.subCategory = data;
        }
      )
    }
    else {
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
    let formData = new FormData();
    formData.append('productInfo', new Blob([JSON.stringify(this.product)], {type: 'application/json' }) );
    if (this.imageFile) {
      formData.append('image', this.imageFile);
    }
    this.productService.saveProduct(formData);
  }

  onImageSelected(event: any) {
    this.imageFile = event.target.files[0];
  }
}

