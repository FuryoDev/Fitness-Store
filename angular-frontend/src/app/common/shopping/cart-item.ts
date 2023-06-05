import {Cart} from "./cart";
import {ProductInfo} from "./product-info";
import {Stock} from "../prod-details/stock";

export class CartItem {
  cartItemId!: number
  cart!: Cart
  productInfo!: ProductInfo
  quantity!: number
  totalPrice!: number
  relatedSizeAndStock!: Stock
}
