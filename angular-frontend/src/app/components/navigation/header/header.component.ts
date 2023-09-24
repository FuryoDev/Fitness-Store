import {Component, OnInit} from '@angular/core';
import {Category} from "../../../common/prod-details/category";
import {CategoryService} from "../../../services/prod-details/category/category.service";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";
import {faCartShopping} from "@fortawesome/free-solid-svg-icons/faCartShopping";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {HeaderSharedService} from "../../../services/shared/header-shared.service";
import {Cart} from "../../../common/shopping/cart";
import {CartService} from "../../../services/shopping/cart/cart.service";
import {GuestCartService} from "../../../services/shopping/cart/guest-cart.service";
import {CartItem} from "../../../common/shopping/cart-item";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  cart: Cart = new Cart();
  cartItems: CartItem[] = [];
  isAdmin: boolean = false;
  isLoggedIn: boolean = false;
  faUser = faUser
  faCart = faCartShopping
  categories: Category[] = [];
  numberOfProductsInCart: number = 0;

  constructor(private categoryService: CategoryService,
              private headerService: HeaderSharedService,
              private tokenStorageService: TokenStorageService,
              private cartService: CartService,
              private guestCartService: GuestCartService) {
  }

   ngOnInit() {
    this.headerService.reloadHeader$.subscribe(() => {
      this.loadHeaderData();
    });
  }

  async loadHeaderData() {
    console.log('Header component reloaded.');
    this.retrieveCategories();
    this.isUserLoggedIn();
    this.isUserAdmin();
    await Promise.all([this.retrieveCart(), this.retrieveCartItems()]);

  }

  retrieveCategories() {
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categories = data;
      }
    )
  }

  isUserLoggedIn() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

  isUserAdmin() {
    let roles = this.tokenStorageService.getUser().roles;
    this.isAdmin = !!roles.includes("ADMIN");
  }

  async retrieveCart(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      if (this.tokenStorageService.getUser() === undefined) {
        // Use GuestCartService
        this.cart = this.guestCartService.retrieveCart();
        console.log("Guest Cart service");
        resolve();
      } else {
        // Use CartService
        this.cartService.retrieveCart().subscribe(
          data => {
            if (data != undefined) {
              this.cart = data;
              console.log("Real Cart service");
              resolve();
            }
          },
          error => {
            console.log(error);
            reject(error);
          }
        );
      }
    });
  }


  async retrieveCartItems() {
    await this.retrieveCart();

    if (this.tokenStorageService.getUser() === undefined) {
      // Use GuestCartService
      this.cartItems = this.guestCartService.retrieveCartItems();
    } else {
      // Use CartService
      this.cartService.retrieveCartItems(this.cart.cartId).subscribe(
        data => {
          this.cartItems = data;
          console.log("Ah ouiiiii" + data);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
}
