import { TestBed } from '@angular/core/testing';

import { AdminrewardsService } from './adminrewards.service';

describe('AdminrewardsService', () => {
  let service: AdminrewardsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminrewardsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
