import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../../services/product.service";
import {ProductInfo} from "../../../common/shopping/product-info";

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.sass']
})
export class AdminProductsComponent implements OnInit {

  products: ProductInfo[] = [];
  subCategoryId: number = 9999;

  constructor(private route: ActivatedRoute,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.productsBySubCategory();
    });
  }

  productsBySubCategory() {
    const hasSubCategoryId: boolean = this.route.snapshot.paramMap.has('subCatId')

    if(hasSubCategoryId){
      this.subCategoryId = +this.route.snapshot.paramMap.get('subCatId')!;
    }
    else {
      this.subCategoryId = 1000;
    }
    this.productService.getProductsBySubCategory(this.subCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
