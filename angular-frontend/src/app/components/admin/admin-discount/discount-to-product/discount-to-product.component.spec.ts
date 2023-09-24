import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscountToProductComponent } from './discount-to-product.component';

describe('DiscountToProductComponent', () => {
  let component: DiscountToProductComponent;
  let fixture: ComponentFixture<DiscountToProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiscountToProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiscountToProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
