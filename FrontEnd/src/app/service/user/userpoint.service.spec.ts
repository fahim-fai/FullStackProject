import { TestBed } from '@angular/core/testing';

import { UserpointService } from './userpoint.service';

describe('UserpointService', () => {
  let service: UserpointService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserpointService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
