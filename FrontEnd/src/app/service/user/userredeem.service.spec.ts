import { TestBed } from '@angular/core/testing';

import { UserredeemService } from './userredeem.service';

describe('UserredeemService', () => {
  let service: UserredeemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserredeemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
