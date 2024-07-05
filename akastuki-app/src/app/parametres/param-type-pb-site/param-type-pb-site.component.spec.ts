import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypePbSiteComponent } from './param-type-pb-site.component';

describe('ParamTypePbSiteComponent', () => {
  let component: ParamTypePbSiteComponent;
  let fixture: ComponentFixture<ParamTypePbSiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypePbSiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypePbSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
