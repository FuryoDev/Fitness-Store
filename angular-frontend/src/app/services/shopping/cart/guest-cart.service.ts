import { Injectable } from '@angular/core';
import { Cart } from "../../../common/shopping/cart";
import { CartItem } from "../../../common/shopping/cart-item";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GuestCartService {

  guestCart: Cart = new Cart();
  cartItems: CartItem[] = [];

  constructor() { }

  retrieveCart() {
    return this.guestCart;
  }

  retrieveCartItems() {
    return this.cartItems;
  }

  updateCartItem(cartItem: CartItem) {
    const index = this.cartItems.findIndex(item => item.cartItemId === cartItem.cartItemId);
    if (index !== -1) {
      this.cartItems[index] = cartItem;
    }
  }

  deleteCartItem(cartItemId: number) {
    const index = this.cartItems.findIndex(item => item.cartItemId === cartItemId);
    if (index !== -1) {
      this.cartItems.splice(index, 1);
    }
  }
}
