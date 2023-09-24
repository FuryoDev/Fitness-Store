import {Component, Input, OnInit} from '@angular/core';
import {ProductInfo} from "../../common/shopping/product-info";
import {ProductService} from "../../services/product.service";

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.sass']
})
export class SliderComponent implements OnInit {

  @Input() productType!: string;
  products: ProductInfo[] = [];
  title: string = "";

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    switch (this.productType) {
      case 'latest':
        this.productService.getLatestProducts().subscribe(
          data => {
            this.products = data;
            this.title = "Check our latest products";
            console.log("Ah oui oui sisi");
          }
        );
        break;
      case 'lowStock':
        this.productService.getOutOfStockProducts().subscribe(
          data => {
            this.products = data;
            this.title = "Quick, before they get out of stock";
            console.log("Ah oui oui sisi");
          }
        );
        break;
      case 'discount':
        this.productService.getDiscountedProducts().subscribe(
          data => {
            this.products = data;
            this.title = "Check these discounts";
            console.log("Ah oui oui sisi");
          }
        );
        break;
      default:
        console.error('Unknown product type:', this.productType);
    }
  }
}
