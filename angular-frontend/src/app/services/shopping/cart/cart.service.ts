import {Injectable} from '@angular/core';
import {CartItem} from "../../../common/shopping/cart-item";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../authentication/token-storage.service";
import {Cart} from "../../../common/shopping/cart";
import {BehaviorSubject, Observable} from "rxjs";
import {Stock} from "../../../common/prod-details/stock";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService) {
  }

  //TODO: Implement the no user/cart logic here. We shouldn't user tokenStorageService in the product-page component
  addToCart(cartItem: CartItem) {

    let formData = new FormData();
    formData.append('cartItem', new Blob([JSON.stringify(cartItem)], {type: 'application/json'}));
    formData.append('stock', new Blob([JSON.stringify(cartItem)], {type: 'application/json'}));
    let username = null;
    username = this.tokenStorageService.getUser();
    console.log(username);

    //TODO: Decide how we'll manage if a customer is not logged in
    if (typeof username !== undefined) {
      formData.append('username', username.username);
    }

    return this.httpClient.post<CartItem>(this.baseUrl + 'addToCart', formData);
  }

  retrieveCartItems(cartId: number): Observable<CartItem[]> {
    return this.httpClient.get<CartItem[]>(this.baseUrl + 'retrieveCartItems?cartId=' + cartId);
  }

  retrieveCart(): Observable<Cart> {
    if (this.tokenStorageService.getUser() == null) {
      console.log(this.tokenStorageService.getUser());
      let cart =  new Cart();
      cart.totalPrice = 100;
      const cartSubject = new BehaviorSubject<Cart>(cart);
      return cartSubject.asObservable();
    }

    return this.httpClient.get<Cart>(this.baseUrl + 'retrieveCart?username=' + this.tokenStorageService.getUser().username);
  }
}
