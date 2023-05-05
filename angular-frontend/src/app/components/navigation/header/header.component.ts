import { Component, OnInit } from '@angular/core';
import {Category} from "../../../common/prod-details/category";
import {CategoryService} from "../../../services/prod-details/category/category.service";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";
import {faCartShopping} from "@fortawesome/free-solid-svg-icons/faCartShopping";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class
HeaderComponent implements OnInit {

  isAdmin: boolean = false;
  isLoggedIn: boolean = false;
  faUser = faUser
  faCart = faCartShopping
  categories: Category[] = [];

  constructor(private categoryService: CategoryService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.retrieveCategories();
    this.isUserLoggedIn();
    this.isUserAdmin();
  }

  retrieveCategories() {
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categories = data;
      }
    )
  }

   isUserLoggedIn() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

  isUserAdmin() {
    let roles = this.tokenStorageService.getUser().roles;
    console.log(roles);
    this.isAdmin = !!roles.includes("ADMIN");
  }
}
