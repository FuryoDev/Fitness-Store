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
}
