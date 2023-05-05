import {Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ProductService} from "../../../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {CartItem} from "../../../../common/shopping/cart-item";
import {CartService} from "../../../../services/shopping/cart/cart.service";
import {Stock} from "../../../../common/prod-details/stock";
import {StockService} from "../../../../services/prod-details/stock.service";
import {UserService} from "../../../../services/authentication/user.service";
import {TokenStorageService} from "../../../../services/authentication/token-storage.service";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.sass']
})
export class ProductPageComponent implements OnInit {

  productInfo: ProductInfo = new ProductInfo();
  cartItem!: CartItem;
  currentProductId: number = 1;
  stocks!: Stock[];


  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private stockService: StockService,
              private cartService: CartService,
              private tokenStorageService: TokenStorageService) {
  }

  async ngOnInit() {
    this.cartItem = new CartItem();
    this.cartItem.quantity = 1;
    // use Promise.all() to wait for both getProductById() and getAStock() to complete
    await Promise.all([this.getProductById(), this.getAStock()]);
  }

  async getAStock() {
    // wait for getProductById() to complete before accessing its data
    await this.getProductById();

    if(this.productInfo !== null) {
      this.stockService.findStockByProduct(this.productInfo.productId).subscribe(data => {
        this.stocks = data;
      })
    }
  }

  async getProductById() {
    this.currentProductId = +this.route.snapshot.paramMap.get('prodId')!;

    // set the productInfo value and wait for it to complete
    await this.productService.getProductById(this.currentProductId).toPromise().then(
      data => {
        if(data != undefined)
          this.productInfo = data;
      }
    );
  }


  addToCart() {
    let user = this.tokenStorageService.getUser();
    //TODO: Check if enough in stock
    this.cartItem.productInfo = this.productInfo;
    this.cartService.addToCart(this.cartItem).subscribe()
  }

  increment() {
    this.cartItem.quantity++;
  }

  decrement() {
    this.cartItem.quantity--;
    if (this.cartItem.quantity == 0)
      this.cartItem.quantity = 1;
  }
}
