import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamParamPbSiteComponent } from './param-pb-site.component';

describe('ParamParamPbSiteComponent', () => {
  let component: ParamParamPbSiteComponent;
  let fixture: ComponentFixture<ParamParamPbSiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamParamPbSiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamParamPbSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
