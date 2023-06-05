import {ProductInfo} from "../shopping/product-info";
import {AccountInfo} from "./account-info";
import {PaymentMethod} from "../shopping/payment-method";
import {ShippingAddress} from "./shipping-address";

export class OrderInfo {
  orderId!: number;
  purchaseDate!: string;
  pending!: boolean;
  total!: number;
  paymentMethod!: PaymentMethod;
  shippingAddress!: ShippingAddress;
}
