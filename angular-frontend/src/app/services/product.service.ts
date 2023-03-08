import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductInfo} from "../common/shopping/product-info";
import {map} from 'rxjs/operators'
import {CartItem} from "../common/shopping/cart-item";
import {Stock} from "../common/prod-details/stock";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:6969/';

  constructor(private httpClient: HttpClient) {
  }

  getStock(): Observable<Stock> {
    return this.httpClient.get<Stock>(this.baseUrl + 'oneStock');
  }

  getAllProducts(): Observable<ProductInfo[]> {
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'products');
  }

  //TODO: Add a parameter here
  getProductsByCategory(categoryId: number) {
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'by-category?catId=' + categoryId)
  }

  getProductsBySubCategory(subCategoryId: number) {
    // return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'by-subCategory?subCatId='+ subCategoryId)
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'getAllProducts')
  }

  getProductById(productId: number) {
    return this.httpClient.get<ProductInfo>(this.baseUrl + 'getProductById?id=' + productId)
  }

  saveProduct(product: ProductInfo) {
    return this.httpClient.post(this.baseUrl + 'saveProduct', product);
  }

  deleteProduct(productId: number) {
    return this.httpClient.delete(this.baseUrl + 'deleteProduct?i='+ productId);
  }
}

interface GetResponse {
  products: ProductInfo[];
}
