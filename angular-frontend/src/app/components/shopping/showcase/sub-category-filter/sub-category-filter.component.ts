import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SubCategoryService} from "../../../../services/prod-details/subcategory/sub-category.service";
import {SubCategory} from "../../../../common/prod-details/sub-category";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-sub-category-filter',
  templateUrl: './sub-category-filter.component.html',
  styleUrls: ['./sub-category-filter.component.sass']
})
export class SubCategoryFilterComponent implements OnInit {

  categoryId: number = 1;
  subCategories: SubCategory[] = [];
  selectedSubCategories: SubCategory[] = [];
  @Output() filters = new EventEmitter<SubCategory>();
  constructor(private route: ActivatedRoute, private subCategoryService: SubCategoryService) { }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
      this.subCategoriesByCategory();
    });
  }

  onFiltersChange(filter: SubCategory): void {
    console.log(filter);
    this.filters.emit(filter);
  }
  subCategoriesByCategory() {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('catId')
    if(hasCategoryId) {
      this.categoryId = +this.route.snapshot.paramMap.get('catId')!;
    }
    else {
      console.log("Pas de category ID pour admin menu")
    }

    this.subCategoryService.getFiltersByCategory(this.categoryId).subscribe(
      data => {
        this.subCategories = data;
      },
      error => {
        console.log(error)
      }
    )
  }
}
