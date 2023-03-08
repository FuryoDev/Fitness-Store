import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../services/product.service";
import {ProductInfo} from "../../common/shopping/product-info";
import {Stock} from "../../common/prod-details/stock";

@Component({
  selector: 'app-tempo',
  templateUrl: './tempo.component.html',
  styleUrls: ['./tempo.component.sass']
})
export class TempoComponent implements OnInit {

  products: ProductInfo[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.listProducts();
  }



  listProducts() {
    this.productService.getAllProducts().subscribe(
      data => {
        this.products = data;
      }
    )
  }

  sendData(product: ProductInfo) {
    this.productService.saveProduct(product).subscribe();
  }
}
