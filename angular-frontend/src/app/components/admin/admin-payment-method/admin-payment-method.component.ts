import { Component, OnInit } from '@angular/core';
import {PaymentMethodService} from "../../../services/shopping/payment-method/payment-method.service";
import {PaymentMethod} from "../../../common/shopping/payment-method";

@Component({
  selector: 'app-admin-payment-method',
  templateUrl: './admin-payment-method.component.html',
  styleUrls: ['./admin-payment-method.component.sass']
})
export class AdminPaymentMethodComponent implements OnInit {

  paymentMethods: PaymentMethod[] = [];
  constructor(private paymentMethodService: PaymentMethodService) { }

  ngOnInit(): void {
    this.paymentMethodService.getAllPaymentMethods().subscribe(
      data => {
        this.paymentMethods = data;
      },
      error => {
        console.log(error);
      }
    )
  }

}
