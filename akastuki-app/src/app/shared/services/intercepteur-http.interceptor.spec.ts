import { TestBed } from '@angular/core/testing';

import { IntercepteurHttpInterceptor } from './intercepteur-http.interceptor';

describe('IntercepteurHttpInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      IntercepteurHttpInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: IntercepteurHttpInterceptor = TestBed.inject(IntercepteurHttpInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
