import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SubCategory} from "../../../common/prod-details/sub-category";

@Injectable({
  providedIn: 'root'
})
export class SubCategoryService {

  private baseUrl = 'http://localhost:6969/';

  constructor(private httpClient: HttpClient) { }

  getSubCategoryById(subCategoryId: number) {
    return this.httpClient.get<SubCategory>(this.baseUrl + 'getSubCategoryById?catId='+ subCategoryId)
  }

  getSubCategoriesByCategory(categoryId: number) {
    return this.httpClient.get<SubCategory[]>(this.baseUrl +'getSubCategoriesByCategory?catId='+ categoryId);
  }

  getAllSubCategories() {
    return this.httpClient.get<SubCategory[]>(this.baseUrl +'getAllSubCategories');
  }

  saveSubCategory(subCategory: SubCategory) {
    return this.httpClient.post(this.baseUrl + 'saveSubCategory',subCategory);
  }
}
