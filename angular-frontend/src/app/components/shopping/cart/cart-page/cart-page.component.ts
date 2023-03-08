import { Component, OnInit } from '@angular/core';
import {Cart} from "../../../../common/shopping/cart";

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.sass']
})
export class CartPageComponent implements OnInit {

  cart = new Cart();

  constructor() { }

  ngOnInit(): void {
    //retrieve cart
  }

}
