import { TestBed } from '@angular/core/testing';

import { FiltersProductSharedService } from './filters-product-shared.service';

describe('FiltersProductSharedService', () => {
  let service: FiltersProductSharedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FiltersProductSharedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
