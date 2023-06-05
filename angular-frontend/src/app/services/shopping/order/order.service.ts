import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CartItem} from "../../../common/shopping/cart-item";
import {OrderInfo} from "../../../common/users/orderInfo";
import {TokenStorageService} from "../../authentication/token-storage.service";
import {OrderItem} from "../../../common/users/order-item";
import {ShippingAddress} from "../../../common/users/shipping-address";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient, private tokenStorageService: TokenStorageService) {

  }

  getOrderById(orderId: number) {
    return this.httpClient.get<OrderInfo>(this.baseUrl + 'getOrderById?orderId=' + orderId);
  }

  retrieveOrders(username: string) {
    return this.httpClient.get<OrderInfo[]>(this.baseUrl +'retrieveOrders?username=' + username);
  }

  retrieveOrderItems(orderId: number) {
    return this.httpClient.get<OrderItem[]>(this.baseUrl + 'retrieveOrderItems?orderId=' + orderId);
  }
  processCheckout(orderInfo: OrderInfo, cartItems: CartItem[], username: string, shippingAddress: ShippingAddress) {
    let formData = new FormData();
    formData.append('username', username)
    formData.append('shippingAddress',new Blob([JSON.stringify(shippingAddress)], {type: 'application/json' }));
    formData.append('cartItems', new Blob([JSON.stringify(cartItems)], {type: 'application/json' }));
    formData.append('orderInfo', new Blob([JSON.stringify(orderInfo)], {type: 'application/json' }));

    return this.httpClient.post<CartItem>(this.baseUrl + 'validateCart', formData);
  }
}
