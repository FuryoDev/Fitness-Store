import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {Category} from "../../../../common/prod-details/category";
import {CategoryService} from "../../../../services/prod-details/category/category.service";
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sub-category-form',
  templateUrl: './sub-category-form.component.html',
  styleUrls: ['./sub-category-form.component.sass']
})
export class SubCategoryFormComponent implements OnInit {

  subCategoryForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required),
    image: new FormControl('', Validators.required)
  });
  currentCategory=  new Category();
  subCategory = new SubCategory();
  categoryList : Category[] = []
  imageFile: File | null = null;
  errorMessage: string | undefined;

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
    this.subCategoryService.saveSubCategory(this.subCategory, this.imageFile).subscribe(
      data => {
        console.log(data);
      },
      error => {
        this.errorMessage = error.error.message;;
      }
    );
  }

  onImageSelected(event: any) {
    this.imageFile = event.target.files[0];
  }

  get name() {return this.subCategoryForm.get('name'); }
  get category() {return this.subCategoryForm.get('category'); }
  get image() {return this.subCategoryForm.get('image'); }
}
