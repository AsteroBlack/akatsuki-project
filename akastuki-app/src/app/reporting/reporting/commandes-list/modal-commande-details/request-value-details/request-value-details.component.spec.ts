import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestValueDetailsComponent } from './request-value-details.component';

describe('RequestValueDetailsComponent', () => {
  let component: RequestValueDetailsComponent;
  let fixture: ComponentFixture<RequestValueDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestValueDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestValueDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
