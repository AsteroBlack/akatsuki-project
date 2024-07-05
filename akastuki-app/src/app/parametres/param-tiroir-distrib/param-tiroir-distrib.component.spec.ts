import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamPdTypeComponent } from './param-pd-type.component';

describe('ParamPdTypeComponent', () => {
  let component: ParamPdTypeComponent;
  let fixture: ComponentFixture<ParamPdTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamPdTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamPdTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
