import { TestBed } from '@angular/core/testing';

import { AdminproductService } from './adminproduct.service';

describe('AdminproductService', () => {
  let service: AdminproductService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminproductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
