import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../../common/shopping/cart-item";
import {OrderService} from "../../../services/shopping/order/order.service";
import {OrderInfo} from "../../../common/users/orderInfo";
import {CartService} from "../../../services/shopping/cart/cart.service";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {Cart} from "../../../common/shopping/cart";
import {PaymentMethod} from "../../../common/shopping/payment-method";
import {PaymentMethodService} from "../../../services/shopping/payment-method/payment-method.service";
import {ShippingAddress} from "../../../common/users/shipping-address";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.sass']
})
export class CheckoutPageComponent implements OnInit {

  checkoutForm: FormGroup = new FormGroup({
    street: new FormControl('', Validators.required),
    city: new FormControl(0, Validators.required),
    country: new FormControl('unisex', Validators.required),
    paymentMethod: new FormControl('', Validators.required)
  });

  cart: Cart = new Cart();
  orderInfo: OrderInfo = new OrderInfo();
  cartItems: CartItem[] = [];
  paymentMethods: PaymentMethod[] = [];
  shippingAddress: ShippingAddress = new ShippingAddress();

  constructor(private orderService: OrderService,
              private cartService: CartService,
              private tokenStorageService: TokenStorageService,
              private paymentMethodService: PaymentMethodService) {
  }

  async ngOnInit() {
    //TODO: Transfer this cause it's duplicated code
    this.retrievePaymentMethods();
    await Promise.all([this.retrieveUserCart(), this.retrieveCartItems()])
    this.updateCartTotal();
  }

  async retrieveCartItems() {
    await this.retrieveUserCart();

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

  async retrieveUserCart() {
    await this.cartService.retrieveCart().toPromise().then(
      data => {
        if (data != undefined)
          this.cart = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  retrievePaymentMethods() {
    this.paymentMethodService.getAllPaymentMethods().subscribe(
      data => {
        this.paymentMethods = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  validateCheckout() {
    this.orderService.processCheckout(this.orderInfo, this.cartItems, this.tokenStorageService.getUser().username, this.shippingAddress).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error);
      }
    )
  }

  updateCartTotal() {
    this.cart.totalPrice = this.cartItems.reduce(
      (total, cartItem) => total + cartItem.totalPrice,
      0
    );
  }

  get street() {return this.checkoutForm.get('street'); }
  get city() { return this.checkoutForm.get('city'); }
  get country() { return this.checkoutForm.get('country'); }
  get paymentMethod() { return this.checkoutForm.get('paymentMethod'); }
}
