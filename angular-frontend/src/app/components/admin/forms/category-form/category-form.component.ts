import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CategoryService} from "../../../../services/prod-details/category/category.service";
import {Category} from "../../../../common/prod-details/category";
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.sass']
})
export class CategoryFormComponent implements OnInit {

  categoryForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    sizingType: new FormControl('', Validators.required),
    image: new FormControl('', Validators.required)
  });
  category = new Category();
  imageFile: File | null = null
  //TODO: Fix this hardcoded shit
  sizingTypes: Array<String> = ['Clothing', 'Shoe', 'None'];
  errorMessage: string | undefined;

  constructor(private router: Router,
              private route: ActivatedRoute,
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
    this.categoryService.saveCategory(this.category, this.imageFile).subscribe(
      data => {
        //TODO: Make an alert that says ctegory saved + redirect to previous page
        console.log(data);
        this.router.navigate(['/admin-dashboard']).then(r => console.log(r));
        window.location.reload();
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }
  onImageSelected(event: any) {
    this.imageFile = event.target.files[0];
  }

  get name() {return this.categoryForm.get('name'); }
  get sizingType() {return this.categoryForm.get('sizingType'); }
  get image() {return this.categoryForm.get('image'); }
}
