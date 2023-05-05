import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../../../common/prod-details/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient) {

  }

  getCategoryById(categoryId: number): Observable<Category> {
    return this.httpClient.get<Category>(this.baseUrl +'admin/getCategoryById?catId='+ categoryId)
  }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.baseUrl + 'admin/getAllCategories');
  }

  saveCategory(category: Category) {
    return this.httpClient.post(this.baseUrl +'admin/saveCategory', category);
  }
}
