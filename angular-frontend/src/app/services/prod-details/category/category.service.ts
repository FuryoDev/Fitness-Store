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
    return this.httpClient.get<Category>(this.baseUrl + 'admin/getCategoryById?catId=' + categoryId)
  }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.baseUrl + 'admin/getAllCategories');
  }

  saveCategory(category: Category, imageFile: File | null) {
    let formData = new FormData();
    formData.append('category', new Blob([JSON.stringify(category)], {type: 'application/json' }) );
    if (category) {
      if(imageFile !== null) {
        formData.append('image', imageFile);
      }
    }
    return this.httpClient.post(this.baseUrl + 'admin/saveCategory', formData);
  }

  deleteCategory(categoryId: number) {
    return this.httpClient.delete(this.baseUrl + 'admin/deleteCategory?categoryId=' + categoryId);
  }
}
