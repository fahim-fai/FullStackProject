import { TestBed } from '@angular/core/testing';

import { AdminallocationService } from './adminallocation.service';

describe('AdminallocationService', () => {
  let service: AdminallocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminallocationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
