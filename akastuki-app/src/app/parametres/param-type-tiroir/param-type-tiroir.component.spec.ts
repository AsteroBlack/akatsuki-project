import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypeTiroirComponent } from './param-type-tiroir.component';

describe('ParamTypeTiroirComponent', () => {
  let component: ParamTypeTiroirComponent;
  let fixture: ComponentFixture<ParamTypeTiroirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypeTiroirComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypeTiroirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
