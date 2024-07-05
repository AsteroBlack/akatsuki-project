import { TestBed } from '@angular/core/testing';

import { PlateformeService } from './plateforme.service';

describe('PlateformeService', () => {
  let service: PlateformeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlateformeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
