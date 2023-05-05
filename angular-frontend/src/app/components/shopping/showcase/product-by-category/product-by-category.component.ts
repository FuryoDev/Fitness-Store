import {Component, Input, OnInit} from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ProductService} from "../../../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {SubCategory} from "../../../../common/prod-details/sub-category";

@Component({
  selector: 'app-product-by-category',
  templateUrl: './product-by-category.component.html',
  styleUrls: ['./product-by-category.component.sass']
})
export class ProductByCategoryComponent implements OnInit {

  selectedSubCategories: SubCategory[] = [];

  @Input() products: ProductInfo[] = [];
  currentCategoryId: number = 1;

  constructor(private route: ActivatedRoute,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.productsByCategories();
    });
  }

  onCheckedFilter($event: any) {
    let index = this.selectedSubCategories.indexOf($event);
    if(index === -1) {
      this.selectedSubCategories.push($event);
    }
    else {
      this.selectedSubCategories.splice(index, 1);
    }
    this.products = this.products.filter(
      product => {
        return this.selectedSubCategories.some(subCategory =>
          subCategory.subCategoryId === product.subCategory.subCategoryId
        )}
    )

    //TODO: Extract the rest of the code in its own method

    if(this.selectedSubCategories.length < 1) {
      this.productsByCategories();
    }
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
    this.productService.getProductsByCategory(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
