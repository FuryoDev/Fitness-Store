import {AccountInfo} from "./account-info";

export class ShippingAddress {
  shippingAddressId!:number;
  streetNameAndNumber!:string;
  zipCode!:string;
  city!:string;
  country!: string;
  customer!:AccountInfo;
}
