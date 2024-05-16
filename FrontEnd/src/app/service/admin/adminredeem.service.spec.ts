import { TestBed } from '@angular/core/testing';

import { AdminredeemService } from './adminredeem.service';

describe('AdminredeemService', () => {
  let service: AdminredeemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminredeemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
