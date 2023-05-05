import { Injectable } from '@angular/core';
import {SubCategory} from "../../common/prod-details/sub-category";
import {ProductInfo} from "../../common/shopping/product-info";

@Injectable({
  providedIn: 'root'
})
export class FiltersProductSharedService {

  filters: SubCategory[] =[];
  products: ProductInfo[] = [];
  constructor() { }

  filterProducts(): ProductInfo[] {
    return this.products.filter(product => {
      return this.filters.some(filter => filter.subCategoryId === product.subCategory.subCategoryId);
    });
  }
}
