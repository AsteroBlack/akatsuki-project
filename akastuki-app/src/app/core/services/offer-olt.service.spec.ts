import { TestBed } from '@angular/core/testing';

import { OfferOltService } from './offer-olt.service';

describe('OfferOltService', () => {
  let service: OfferOltService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfferOltService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
