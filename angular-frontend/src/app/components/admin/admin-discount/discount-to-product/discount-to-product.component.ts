import {Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../../../common/shopping/product-info";
import {ProductService} from "../../../../services/product.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {Category} from "../../../../common/prod-details/category";
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {CategoryService} from "../../../../services/prod-details/category/category.service";

@Component({
  selector: 'app-discount-to-product',
  templateUrl: './discount-to-product.component.html',
  styleUrls: ['./discount-to-product.component.sass']
})
export class DiscountToProductComponent implements OnInit {

  products: ProductInfo[] = [];

  searchTerm: string = '';
  selectedCategories: { [key: string]: boolean } = {};
  selectedSubCategories: { [key: string]: boolean } = {};
  categories: Category[] = []; // Votre liste de catégories
  subCategories: SubCategory[] = []; // Votre liste de sous-catégories
  filteredProducts: ProductInfo[] = [];

  constructor(private productService: ProductService,
              private subCategoryService: SubCategoryService,
              private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(
      data => {
        this.products = data;
        this.filteredProducts = [...this.products]; // Initialisez filteredProducts avec products
        this.filterProducts(); // Filtrez les produits dès le début pour prendre en compte les valeurs par défaut des filtres
      }
    );
    this.subCategoryService.getAllSubCategories().subscribe(
      data => {
        this.subCategories = data;
      }
    );
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.categories = data;
      }
    );
  }

  filterProducts() {
    this.filteredProducts = this.products.filter(product => {
      let matchesSearchTerm = this.searchTerm ? product.name.toLowerCase().includes(this.searchTerm.toLowerCase()) : true;
      let matchesCategory = this.selectedCategories[product.subCategory.category.name] || !Object.values(this.selectedCategories).some(val => val);
      let matchesSubCategory = this.selectedSubCategories[product.subCategory.name] || !Object.values(this.selectedSubCategories).some(val => val);
      return matchesSearchTerm && matchesCategory && matchesSubCategory;
    });
  }

}
