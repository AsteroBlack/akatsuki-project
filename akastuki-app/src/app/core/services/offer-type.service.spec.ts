import { TestBed } from '@angular/core/testing';

import { OfferTypeService } from './offer-type.service';

describe('OfferTypeService', () => {
  let service: OfferTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfferTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
