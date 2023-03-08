import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {Category} from "../../../../common/prod-details/category";
import {CategoryService} from "../../../../services/prod-details/category/category.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-sub-category-form',
  templateUrl: './sub-category-form.component.html',
  styleUrls: ['./sub-category-form.component.sass']
})
export class SubCategoryFormComponent implements OnInit {

  currentCategory=  new Category();
  subCategory = new SubCategory();
  categoryList : Category[] = []

  constructor(private route: ActivatedRoute,
              private subCategoryService: SubCategoryService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.route.params.subscribe( params => console.log(params) );
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('catId');
      this.route.params.subscribe(() => {
        this.initSubCategoryForm(hasCategoryId);
      })
  }

  initSubCategoryForm(hasCategoryId: boolean) {
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categoryList = data;
      }
    )
    if(hasCategoryId) {
      const categoryId: number = +this.route.snapshot.paramMap.get('catId')!;
      this.categoryService.getCategoryById(categoryId).subscribe(
        data => {
          this.currentCategory = data;
        }
      )
    }
    else {
      const subCategoryId: number = +this.route.snapshot.paramMap.get('id')!;
      this.subCategoryService.getSubCategoryById(subCategoryId).subscribe(
        data => {
          this.subCategory = data;
        }
      )
    }
  }

  saveSubCategory() {
    console.log(this.subCategory);
    this.subCategoryService.saveSubCategory(this.subCategory).subscribe(
      result => console.log("SubCat save")
    )
  }
}
