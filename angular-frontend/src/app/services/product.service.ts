import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductInfo} from "../common/shopping/product-info";
import {map} from 'rxjs/operators'
import {CartItem} from "../common/shopping/cart-item";
import {Stock} from "../common/prod-details/stock";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:6969/api/';

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
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'getProductsByCategory?catId=' + categoryId)
  }

  getProductsBySubCategory(subCategoryId: number) {
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'admin/getProductsBySubCategory?subCatId='+ subCategoryId)
  }

  getProductsBySubCat(subCategoryId: number) {
    return this.httpClient.get<ProductInfo[]>(this.baseUrl + 'getProductsBySubCat?subCatId='+ subCategoryId)
  }

  getProductById(productId: number) {
    return this.httpClient.get<ProductInfo>(this.baseUrl + 'get-product?id=' + productId)
  }

  getProductByIdAdmin(productId: number) {
    return this.httpClient.get<ProductInfo>(this.baseUrl + 'admin/getProductById?id=' + productId)
  }

  saveProduct(productFormData: FormData) {
    return this.httpClient.post(this.baseUrl + 'admin/saveProduct', productFormData).subscribe(
      value => console.log(value),
      error => console.log(error));
  }

  deleteProduct(productId: number) {
    return this.httpClient.delete(this.baseUrl + 'admin/deleteProduct?i='+ productId);
  }
}

interface GetResponse {
  products: ProductInfo[];
}
