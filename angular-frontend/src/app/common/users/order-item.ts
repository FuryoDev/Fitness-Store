import {ProductInfo} from "../shopping/product-info";
import {OrderInfo} from "./orderInfo";

export class OrderItem {

  orderItemId!: number;
  product!: ProductInfo;
  order!: OrderInfo;
  purchasedPrice!: number;
  size!: string;
  quantity!: number;
}
