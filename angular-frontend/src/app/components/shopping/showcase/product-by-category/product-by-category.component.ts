import { Component, OnInit } from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ProductService} from "../../../../services/product.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-by-category',
  templateUrl: './product-by-category.component.html',
  styleUrls: ['./product-by-category.component.sass']
})
export class ProductByCategoryComponent implements OnInit {


  products: ProductInfo[] = [];
  currentCategoryId: number = 1;

  constructor(private route: ActivatedRoute,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.productsByCategories();
    });
  }

  productsByCategories() {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('catId')

    if(hasCategoryId){
      this.currentCategoryId = +this.route.snapshot.paramMap.get('catId')!;
    }
    else {
      this.currentCategoryId = 1000;
    }
    console.log(this.currentCategoryId);

    // this.productService.getProductsByCategory(this.currentCategoryId).subscribe(
    //   data => {
    //     this.products = data;
    //   }
    // )
    this.productService.getProductsBySubCategory(1).subscribe(
      data => {
        this.products = data;
      }
    )
  }

}
