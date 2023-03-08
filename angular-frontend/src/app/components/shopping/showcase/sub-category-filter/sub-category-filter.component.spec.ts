import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubCategoryFilterComponent } from './sub-category-filter.component';

describe('SubCategoryFilterComponent', () => {
  let component: SubCategoryFilterComponent;
  let fixture: ComponentFixture<SubCategoryFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubCategoryFilterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubCategoryFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
