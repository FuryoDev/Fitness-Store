import { Component, OnInit } from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ProductService} from "../../../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {CartItem} from "../../../../common/shopping/cart-item";
import {CartService} from "../../../../services/shopping/cart/cart.service";
import {Stock} from "../../../../common/prod-details/stock";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.sass']
})
export class ProductPageComponent implements OnInit {

  productInfo!: ProductInfo;
  cartItem!: CartItem;
  currentProductId: number = 1;
  stock!: Stock;


  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.productById();
    })
    this.getAStock();
  }

  getAStock() {
    this.productService.getStock().subscribe(data => {
      this.stock = data;
    })
  }

  productById() {
    const hasProductId: boolean = this.route.snapshot.paramMap.has('prodId');
    if(hasProductId) {
      this.currentProductId = +this.route.snapshot.paramMap.get('prodId')!;
    }
    else {
      this.currentProductId = 9999;
    }

    this.productService.getProductById(this.currentProductId).subscribe(
      data => {
        this.productInfo = data;
      }
    );
  }


  addToCart() {
    this.cartService.addToCart(this.cartItem).subscribe()
  }

  increment() {
    this.cartItem.quantity++;
  }

  decrement() {
    this.cartItem.quantity--;
    if(this.cartItem.quantity == 0)
      this.cartItem.quantity = 1;
  }
}
