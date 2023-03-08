import {Discount} from "./discount";
import {SubCategory} from "./sub-category";
import {ProductInfo} from "../shopping/product-info";

export class Stock {
  constructor(public stockId: number,
              public size: string,
              public itemsInStock: number,
              public maxItems: number,
              public product: ProductInfo) {
  }
}
