import {Cart} from "./cart";
import {ProductInfo} from "./product-info";

export class CartItem {
  constructor(public cartItemId: number,
              public cart: Cart,
              public productInfo: ProductInfo,
              public quantity: number,
              public totalPrice: number,
              public size: string) {
  }
}
