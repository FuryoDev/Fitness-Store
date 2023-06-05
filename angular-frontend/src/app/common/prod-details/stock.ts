import {Discount} from "./discount";
import {SubCategory} from "./sub-category";
import {ProductInfo} from "../shopping/product-info";

export class Stock {
  stockId!: number
  size!: string
  itemsInStock!: number
  maxItems!: number
  product!: ProductInfo
}
