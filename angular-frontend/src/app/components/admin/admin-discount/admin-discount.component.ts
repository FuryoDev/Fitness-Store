import { Component, OnInit } from '@angular/core';
import {Discount} from "../../../common/prod-details/discount";
import {DiscountService} from "../../../services/prod-details/discount/discount.service";
import {subscribeOn} from "rxjs";

@Component({
  selector: 'app-admin-discount',
  templateUrl: './admin-discount.component.html',
  styleUrls: ['./admin-discount.component.sass']
})
export class AdminDiscountComponent implements OnInit {

  discounts: Discount[] = [];

  constructor(private discountService: DiscountService) { }

  ngOnInit(): void {
    this.discountService.retrieveDiscounts().subscribe(
      data => {
        this.discounts = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  deleteDiscount(discountId: number) {
    this.discountService.deleteDiscount(discountId).subscribe(
      resp => {
        console.log(resp)
      },
      error => {
        console.log(error);
      }
    )
  }
}
