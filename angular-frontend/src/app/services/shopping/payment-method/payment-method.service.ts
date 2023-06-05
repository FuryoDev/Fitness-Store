import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PaymentMethod} from "../../../common/shopping/payment-method";

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient) { }

  getPaymentMethodById(id: number) {
    return this.httpClient.get<PaymentMethod>(this.baseUrl + 'admin/getPaymentMethodById?paymentMethodId=' + id)
  }

  getAllPaymentMethods() {
    return this.httpClient.get<PaymentMethod[]>(this.baseUrl + 'admin/getAllPaymentMethods');
  }

  savePaymentMethod(paymentMethod: PaymentMethod, imageFile: File | null) {
    let formData = new FormData();
    formData.append('paymentMethod', new Blob([JSON.stringify(paymentMethod)], {type: 'application/json' }) );
    if (imageFile) {
      formData.append('image', imageFile);
    }
    return this.httpClient.post(this.baseUrl + 'admin/savePaymentMethod', formData);
  }
}
