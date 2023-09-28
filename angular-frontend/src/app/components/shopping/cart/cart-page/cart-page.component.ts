import { Component, OnInit } from '@angular/core';
import { Cart } from "../../../../common/shopping/cart";
import { CartService } from "../../../../services/shopping/cart/cart.service";
import { CartItem } from "../../../../common/shopping/cart-item";
import { GuestCartService } from "../../../../services/shopping/cart/guest-cart.service";
import { TokenStorageService } from "../../../../services/authentication/token-storage.service";
import { HeaderSharedService } from "../../../../services/shared/header-shared.service";

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
    private headerService: HeaderSharedService,
    private guestCartService: GuestCartService,
    private tokenStorageService: TokenStorageService
  ) {}

  async ngOnInit() {
    await Promise.all([this.retrieveUserCart(), this.retrieveCartItems()]);
  }

  async retrieveCartItems() {
    await this.retrieveUserCart();

    if (this.isUserAuthenticated()) {
      this.cartService.retrieveCartItems(this.cart.cartId).subscribe(
        data => {
          this.cartItems = data;
          this.updateCartTotal();
        },
        error => {
          console.log(error);
        }
      );
    } else {
      this.cartItems = this.guestCartService.retrieveCartItems();
    }
  }

  async retrieveUserCart() {
    if (this.isUserAuthenticated()) {
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
    } else {
      this.cart = this.guestCartService.retrieveCart();
    }
  }

  changeQty(value: number, index: number) {
    const cartItem = this.cartItems[index];
    const newValue = cartItem.quantity + value;

    if (newValue >= 1 && newValue <= 10) {
      cartItem.quantity = newValue;
    } else if (newValue < 1) {
      cartItem.quantity = 1;
    } else if (newValue > 10) {
      cartItem.quantity = 10;
    }

    cartItem.totalPrice = cartItem.quantity * cartItem.productInfo.price;

    if (this.isUserAuthenticated()) {
      this.updateCartItem(cartItem);
    } else {
      this.guestCartService.updateCartItem(cartItem);
      this.updateCartTotal();
    }
  }

  removeFromCart(cartItemId: number) {
    if (this.isUserAuthenticated()) {
      this.cartService.deleteCartItem(cartItemId).subscribe(
        response => {
          this.retrieveUserCart();
          this.updateCartTotal();
          this.headerService.reloadHeader();
        }
      );
    } else {
      this.guestCartService.deleteCartItem(cartItemId);
      this.updateCartTotal();
      this.headerService.reloadHeader();
    }
  }

  private isUserAuthenticated(): boolean {
    return this.tokenStorageService.getUser() !== undefined;
  }

  updateCartItem(cartItem: CartItem) {
    this.cartService.updateCartItem(cartItem).subscribe(
      response => {
        this.updateCartTotal();
        this.headerService.reloadHeader();
      }
    )
  }

  updateCartTotal() {
    this.cart.totalPrice = this.cartItems.reduce(
      (total, cartItem) => total + cartItem.totalPrice,
      0
    );
  }
}
