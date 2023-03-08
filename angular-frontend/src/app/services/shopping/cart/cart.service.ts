import { Injectable } from '@angular/core';
import {CartItem} from "../../../common/shopping/cart-item";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:6969/';

  constructor(private httpClient: HttpClient) { }

  addToCart(cartItem: CartItem) {
    return this.httpClient.post<CartItem>(this.baseUrl + 'addToCart', cartItem);
  }
}
