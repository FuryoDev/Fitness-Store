import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SubCategory} from "../../../common/prod-details/sub-category";

@Injectable({
  providedIn: 'root'
})
export class SubCategoryService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private httpClient: HttpClient) { }

  getSubCategoryById(subCategoryId: number) {
    return this.httpClient.get<SubCategory>(this.baseUrl + 'admin/getSubCategoryById?subCatId='+ subCategoryId)
  }

  getSubCategoriesByCategory(categoryId: number) {
    return this.httpClient.get<SubCategory[]>(this.baseUrl +'admin/getSubCategoriesByCategory?catId='+ categoryId);
  }

  getFiltersByCategory(categoryId: number) {
    return this.httpClient.get<SubCategory[]>(this.baseUrl +'getSubCategoriesByCategory?catId='+ categoryId);
  }

  getAllSubCategories() {
    return this.httpClient.get<SubCategory[]>(this.baseUrl +'admin/getAllSubCategories');
  }

  saveSubCategory(subCategory: SubCategory) {
    return this.httpClient.post(this.baseUrl + 'admin/saveSubCategory',subCategory);
  }
}
