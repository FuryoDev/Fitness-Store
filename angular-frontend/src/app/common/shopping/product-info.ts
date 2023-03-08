import {Discount} from "../prod-details/discount";
import {Stock} from "../prod-details/stock";
import {SubCategory} from "../prod-details/sub-category";

export class ProductInfo {
  productId!: number;
  name!: string;
  sex!: string;
  imageURL!: string;
  price!: number;
  description!: string;
  discount!: Discount;
  stocks!: Stock[];
  subCategory!: SubCategory;
}
