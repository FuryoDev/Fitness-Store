import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Discount} from "../../../common/prod-details/discount";

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient) {

  }

  getDiscountById(discountId: number) {
    return this.httpClient.get<Discount>(this.baseUrl + 'admin/getDiscountById?id=' + discountId);
  }

  retrieveDiscounts() {
    return this.httpClient.get<Discount[]>(this.baseUrl + 'admin/getAllDiscounts');
  }

  saveDiscount(discount: Discount){
    console.log(discount);
    let formData = new FormData();
    formData.append('discount', new Blob([JSON.stringify(discount)], {type: 'application/json' }) );
    // if (discount) {
    //   if(imageFile !== null) {
    //     formData.append('image', imageFile);
    //   }
    // }
    return this.httpClient.post(this.baseUrl + 'admin/saveDiscount', formData)
  }

  deleteDiscount(discountId: number) {
    return this.httpClient.delete(this.baseUrl + 'admin/deleteDiscount?discId=' + discountId);
  }
}
