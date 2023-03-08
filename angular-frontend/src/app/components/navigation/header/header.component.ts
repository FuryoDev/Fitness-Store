import { Component, OnInit } from '@angular/core';
import {Category} from "../../../common/prod-details/category";
import {CategoryService} from "../../../services/prod-details/category/category.service";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";
import {faCartShopping} from "@fortawesome/free-solid-svg-icons/faCartShopping";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  faUser = faUser
  faCart = faCartShopping
  categories: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.retrieveCategories();
  }

  retrieveCategories() {
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categories = data;
      }
    )
  }
}
