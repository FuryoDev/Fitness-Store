import {Component, OnInit} from '@angular/core';
import {Cart} from "../../../../common/shopping/cart";
import {CartService} from "../../../../services/shopping/cart/cart.service";
import {CartItem} from "../../../../common/shopping/cart-item";
import {GuestCartService} from "../../../../services/shopping/cart/guest-cart.service";
import {TokenStorageService} from "../../../../services/authentication/token-storage.service";

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.sass']
})
export class CartPageComponent implements OnInit {

  cart: Cart = new Cart();
  cartItems: CartItem[] = [];

  constructor(
    private cartService: CartService,
    private guestCartService: GuestCartService,
    private tokenStorageService: TokenStorageService // Modify with your TokenStorageService
  ) {
  }

  async ngOnInit() {
    await Promise.all([this.retrieveUserCart(), this.retrieveCartItems()]);
  }

  async retrieveCartItems() {
    await this.retrieveUserCart();

    if (this.tokenStorageService.getUser() === undefined) {
      // Use GuestCartService
      this.cartItems = this.guestCartService.retrieveCartItems();
    } else {
      // Use CartService
      this.cartService.retrieveCartItems(this.cart.cartId).subscribe(
        data => {
          this.cartItems = data;
          this.updateCartTotal();
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  async retrieveUserCart() {
    if (this.tokenStorageService.getUser() === undefined) {
      // Use GuestCartService
      this.cart = this.guestCartService.retrieveCart()
    } else {
      // Use CartService
      await this.cartService.retrieveCart().toPromise().then(
        data => {
          if (data != undefined) {
            this.cart = data;
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  changeQty(value: number, index: number) {
    const cartItem = this.cartItems[index];

    const newValue = cartItem.quantity + value;

    if (newValue >= 1 && newValue <= 10) {
      cartItem.quantity = newValue;
      cartItem.totalPrice = cartItem.quantity * cartItem.productInfo.price;
      this.updateCartTotal();
    } else if (newValue < 1) {
      cartItem.quantity = 1;
      cartItem.totalPrice = cartItem.quantity * cartItem.productInfo.price;
      this.updateCartTotal();
    } else if (newValue > 10) {
      cartItem.quantity = 10;
      cartItem.totalPrice = cartItem.quantity * cartItem.productInfo.price;
      this.updateCartTotal();
    }
  }

  removeFromCart(index: number) {
    if (index >= 0 && index < this.cart.cartItems.length) {
      this.cart.cartItems.splice(index, 1);
    }
    this.updateCartTotal();
  }

  updateCartTotal() {
    this.cart.totalPrice = this.cartItems.reduce(
      (total, cartItem) => total + cartItem.totalPrice,
      0
    );
  }
}
