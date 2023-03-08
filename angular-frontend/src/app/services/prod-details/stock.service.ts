import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Stock} from "../../common/prod-details/stock";

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private baseUrl = 'http://localhost:6969/';

  constructor(private httpClient: HttpClient) { }

  findStockByProduct(productId: number): Observable<Stock[]> {
    return this.httpClient.get<Stock[]>(this.baseUrl + 'stocksByProduct?id='+ productId);
  }
}
