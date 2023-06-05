import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPaymentMethodComponent } from './admin-payment-method.component';

describe('AdminPaymentMethodComponent', () => {
  let component: AdminPaymentMethodComponent;
  let fixture: ComponentFixture<AdminPaymentMethodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPaymentMethodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPaymentMethodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
