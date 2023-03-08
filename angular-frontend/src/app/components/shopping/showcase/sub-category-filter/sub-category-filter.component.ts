import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sub-category-filter',
  templateUrl: './sub-category-filter.component.html',
  styleUrls: ['./sub-category-filter.component.sass']
})
export class SubCategoryFilterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("The filters component is INITED");
  }

}
