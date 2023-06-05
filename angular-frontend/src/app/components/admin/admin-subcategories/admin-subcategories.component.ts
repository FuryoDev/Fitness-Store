import { Component, OnInit } from '@angular/core';
import {SubCategory} from "../../../common/prod-details/sub-category";
import {ActivatedRoute} from "@angular/router";
import {SubCategoryService} from "../../../services/prod-details/subcategory/sub-category.service";

@Component({
  selector: 'app-admin-subcategories',
  templateUrl: './admin-subcategories.component.html',
  styleUrls: ['./admin-subcategories.component.sass']
})
export class AdminSubcategoriesComponent implements OnInit {

  categoryId: number = 1;
  subCategories: SubCategory[] = [];

  constructor(private route: ActivatedRoute,
              private subCategoryService: SubCategoryService) { }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
      this.subCategoriesByCategory();
    });
  }

  subCategoriesByCategory() {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('catId')
    if(hasCategoryId) {
      this.categoryId = +this.route.snapshot.paramMap.get('catId')!;
    }
    else {
      console.log("Pas de category ID pour l'admin menu")
    }

    this.subCategoryService.getSubCategoriesByCategory(this.categoryId).subscribe(
      data => {
        this.subCategories = data;
      }
    )
  }

  deleteSubCategory(subCategoryId: number) {
    this.subCategoryService.deleteSubCategory(subCategoryId).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
