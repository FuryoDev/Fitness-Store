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
import {GuestCartService} from "../../../../services/shopping/cart/guest-cart.service";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.sass']
})
export class ProductPageComponent implements OnInit {

  productInfo: ProductInfo = new ProductInfo();
  cartItem!: CartItem;
  currentProductId: number = 0;
  stocks!: Stock[];
  isInStock: boolean = true;
  errorMessage!: string;


  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private stockService: StockService,
              private cartService: CartService,
              private tokenStorageService: TokenStorageService,
              private guestCartService: GuestCartService) {
  }

  async ngOnInit() {
    this.cartItem = new CartItem();
    this.cartItem.quantity = 1;
    await Promise.all([this.getProductById(), this.getAStock()]);
  }

  async getAStock() {
    await this.getProductById();

    if (this.productInfo !== null) {
      this.stockService.findStockByProduct(this.productInfo.productId).subscribe(data => {
        if (data.length == 1) {
          this.cartItem.relatedSizeAndStock = data[0];
        } else {
          this.stocks = data;
        }
      })
    }
  }

  async getProductById() {
    this.currentProductId = +this.route.snapshot.paramMap.get('prodId')!;

    await this.productService.getProductById(this.currentProductId).toPromise().then(
      data => {
        if (data != undefined)
          this.productInfo = data;
      }
    );
  }


  addToCart() {
    this.cartItem.productInfo = this.productInfo;
    if (this.tokenStorageService.getUser() == undefined) {
      this.addToCartTwo(this.cartItem);
    } else {
      this.cartService.addToCart(this.cartItem).subscribe()
    }
  }

  addToCartTwo(cartItem: CartItem) {
    const existingCartItem = this.guestCartService.cartItems.find(item =>
      item.productInfo.productId === cartItem.productInfo.productId && item.relatedSizeAndStock.stockId === cartItem.relatedSizeAndStock.stockId
    );

    if (existingCartItem) {
      existingCartItem.quantity += cartItem.quantity;
    } else {
      this.guestCartService.cartItems.push(cartItem);
    }
  }

  selectSize(size: any) {
    this.cartItem.relatedSizeAndStock = size;
  }

  isSelectedSize(size: Stock): boolean {
    return this.cartItem.relatedSizeAndStock === size;
  }

  isSizeSelected(): boolean {
    return this.cartItem.relatedSizeAndStock !== undefined;
  }

  changeQty(value: number) {
    if (this.cartItem.relatedSizeAndStock == undefined) {
      this.errorMessage = 'Select a size first'
      return;
    } else {
      const newValue = this.cartItem.quantity + value;
      // Si la quantité voulue est plus grande que la quantité en stock, mettre le message d'error et ne pas incrément
      if (newValue > this.cartItem.relatedSizeAndStock.itemsInStock) {
        this.isInStock = false;
        this.errorMessage = 'We don\'t have more than this quantity in stock';
      } else {
        this.isInStock = true;
        if (newValue >= 1 && newValue <= 10) {
          this.cartItem.quantity = newValue;
        } else if (newValue < 1) {
          this.cartItem.quantity = 1;
        } else if (newValue > 10) {
          this.cartItem.quantity = 10;
        }
      }
    }
  }
}
