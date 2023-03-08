import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.sass']
})
export class CustomerPageComponent implements OnInit {

  isPersonalActive: boolean = true;
  isOrdersActive: boolean = false;

  constructor() { }

  ngOnInit(): void {
    this.isPersonalActive = true;
    this.isOrdersActive = false
  }

  togglePersonal() {
    this.isOrdersActive = false;
    this.isPersonalActive = true;
  }

  toggleOrders() {
    this.isPersonalActive = false;
    this.isOrdersActive = true;
  }

}
