import {Stock} from "./stock";
import {SubCategory} from "./sub-category";
import {ProductInfo} from "../shopping/product-info";

export class Discount {
  public discountId!: number;
  public discountName!: string;
  public percentage!: number;
  public isActive!: boolean;
  public discountedProducts!: ProductInfo[];
}
