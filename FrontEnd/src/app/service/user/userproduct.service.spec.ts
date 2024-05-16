import { TestBed } from '@angular/core/testing';

import { UserproductService } from './userproduct.service';

describe('UserproductService', () => {
  let service: UserproductService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserproductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
