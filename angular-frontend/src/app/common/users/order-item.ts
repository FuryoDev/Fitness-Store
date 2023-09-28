import {ProductInfo} from "../shopping/product-info";
import {OrderInfo} from "./orderInfo";
import {Stock} from "../prod-details/stock";

export class OrderItem {

  orderItemId!: number;
  product!: ProductInfo;
  order!: OrderInfo;
  purchasedPrice!: number;
  size!: string;
  quantity!: number;
  relatedStockAndSize!: Stock;
}
