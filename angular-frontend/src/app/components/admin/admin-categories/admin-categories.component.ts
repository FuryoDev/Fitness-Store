import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Category} from "../../../common/prod-details/category";
import {ActivatedRoute, Router} from "@angular/router";
import {CategoryService} from "../../../services/prod-details/category/category.service";
import {HeaderSharedService} from "../../../services/shared/header-shared.service";

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.sass']
})
export class AdminCategoriesComponent implements OnInit {

  categoryList: Category[] = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private headerService: HeaderSharedService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
      this.retrieveAllCategories();
    });
  }

  retrieveAllCategories() {
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categoryList = data;
      }
    )
  }
  deleteCategory(categoryId: number) {
    this.categoryService.deleteCategory(categoryId).subscribe(
      data => {
        console.log(data)
        this.headerService.reloadHeader();
        this.router.navigate(['/admin-dashboard']).then(r => console.log(r));
      },
      error => {
        console.log(error);
      }
    );
  }
}
