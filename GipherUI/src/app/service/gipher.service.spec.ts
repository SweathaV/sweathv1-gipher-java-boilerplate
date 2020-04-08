import { TestBed } from '@angular/core/testing';

import { GipherService } from './gipher.service';

describe('GipherService', () => {
  let service: GipherService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GipherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
