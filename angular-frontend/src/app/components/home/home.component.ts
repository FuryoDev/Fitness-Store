import {Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../common/shopping/product-info";
import {ProductService} from "../../services/product.service";
import {Observable} from "rxjs";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  latestProducts: ProductInfo[] = [];
  discountProducts: ProductInfo[] = [];
  lowStockProducts: ProductInfo[] = [];

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.retrieveProducts();
  }

  retrieveProducts() {
    this.productService.getAllProducts().subscribe(
      data => {
        this.latestProducts = data;
        this.discountProducts = data;
        this.lowStockProducts = data;
      }
    );
  }

  private retrieveOtherProducts() {

  }

  private retrieveThridProducts() {

  }
}
