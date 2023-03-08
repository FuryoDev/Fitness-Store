import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CategoryService} from "../../../../services/prod-details/category/category.service";
import {Category} from "../../../../common/prod-details/category";

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.sass']
})
export class CategoryFormComponent implements OnInit {

  category = new Category();

  sizingTypes: Array<String> = ['Clothing', 'Shoe', 'None'];

  constructor(private route: ActivatedRoute,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.route.params.subscribe(() =>  {
      this.initCategoryForm();
    })
  }

  initCategoryForm(){
    const hasProductId: boolean = this.route.snapshot.paramMap.has('id')
    if(hasProductId) {
      const categoryId: number = +this.route.snapshot.paramMap.get('id')!;
      this.categoryService.getCategoryById(categoryId).subscribe(
        data => {
          this.category = data;
        }
      )
    }
  }

  saveCategory() {
    this.categoryService.saveCategory(this.category).subscribe(
      result => console.log("Category saved")
    )
  }

}
