import { Injectable } from '@angular/core';
import {CartItem} from "../../../common/shopping/cart-item";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../authentication/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService) { }

  addToCart(cartItem: CartItem) {
    let formData = new FormData();
    formData.append('cartItem', new Blob([JSON.stringify(cartItem)], {type: 'application/json' }) );
    let username = null;
    username = this.tokenStorageService.getUser();
    console.log(username);

    //TODO: Decide how we'll manage if a customer is not logged in
    if(typeof username !== undefined) {
      formData.append('username', username.username);
    }

    return this.httpClient.post<CartItem>(this.baseUrl + 'addToCart', formData);
  }
}
