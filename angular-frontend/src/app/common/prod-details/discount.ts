import {Stock} from "./stock";
import {SubCategory} from "./sub-category";
import {ProductInfo} from "../shopping/product-info";

export class Discount {
  constructor(public discountId: number,
              public discountName: string,
              public percentage: number,
              public isActive: boolean,
              public subCategory: SubCategory,
              public discountedProducts: ProductInfo[]) {
  }
}
