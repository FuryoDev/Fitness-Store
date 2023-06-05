import {AccountInfo} from "../users/account-info";
import {CartItem} from "./cart-item";

export class Cart {
  cartId!: number;
  cartItems!: CartItem[];
  totalPrice!: number;
  accountInfo!: AccountInfo;
}
