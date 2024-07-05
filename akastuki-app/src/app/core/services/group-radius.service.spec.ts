import { TestBed } from '@angular/core/testing';

import { GroupRadiusService } from './group-radius.service';

describe('GroupRadiusService', () => {
  let service: GroupRadiusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupRadiusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
