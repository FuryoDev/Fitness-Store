import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../../services/shopping/order/order.service";
import {OrderInfo} from "../../../common/users/orderInfo";
import {OrderItem} from "../../../common/users/order-item";

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.sass']
})
export class OrderPageComponent implements OnInit {

  orderId: number = 0;
  order: OrderInfo = new OrderInfo();
  orderItems: OrderItem[] = [];

  constructor(private route: ActivatedRoute, private orderService: OrderService) { }

  async ngOnInit(): Promise<void> {
    await this.getOrderData();
    await this.retrieveOrderItems();
    await Promise.all([this.getOrderData(), this.retrieveOrderItems()])
  }

  async getOrderData(): Promise<void> {
    this.orderId = +this.route.snapshot.paramMap.get('id')!;
    this.orderService.getOrderById(this.orderId).toPromise().then(
      data => {
        if (data != undefined) {
          this.order = data;
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  async retrieveOrderItems(): Promise<void> {
    await this.getOrderData();

    this.orderService.retrieveOrderItems(this.orderId).subscribe(
      data => {
        this.orderItems = data;
      },
      error => {
        console.log(error);
      }
    )
  }

}
