import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../../services/shopping/order/order.service";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {OrderInfo} from "../../../common/users/orderInfo";

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrls: ['./customer-orders.component.sass']
})
export class CustomerOrdersComponent implements OnInit {

  orders : OrderInfo[] = [];

  constructor(private orderService: OrderService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.retrieveOrdersForUser();
  }

  retrieveOrdersForUser() {
    this.orderService.retrieveOrders(this.tokenStorageService.getUser().username).subscribe(
      data => {
        this.orders = data;
      },
      error => {
        console.log(error);
      }
    )
  }
}
