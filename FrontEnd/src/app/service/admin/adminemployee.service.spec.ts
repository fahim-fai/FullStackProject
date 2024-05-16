import { TestBed } from '@angular/core/testing';

import { AdminemployeeService } from './adminemployee.service';

describe('AdminemployeeService', () => {
  let service: AdminemployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminemployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
