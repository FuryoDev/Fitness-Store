import { Component, OnInit } from '@angular/core';
import {ProductInfo} from "../../../common/shopping/product-info";

@Component({
  selector: 'app-admin-stock',
  templateUrl: './admin-stock.component.html',
  styleUrls: ['./admin-stock.component.sass']
})
export class AdminStockComponent implements OnInit {

  product = new ProductInfo();

  constructor() { }

  ngOnInit(): void {
  }

  saveStocks(){
    console.log('Saving stock');
  }

}
