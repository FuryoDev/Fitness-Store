import {Cart} from "./cart";
import {ProductInfo} from "./product-info";

export class CartItem {
  cartItemId!: number
  cart!: Cart
  productInfo!: ProductInfo
  quantity!: number
  totalPrice!: number
  size!: string
}
