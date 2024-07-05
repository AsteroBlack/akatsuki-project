import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypeFormuleComponent } from './param-type-formule.component';

describe('ParamTypeFormuleComponent', () => {
  let component: ParamTypeFormuleComponent;
  let fixture: ComponentFixture<ParamTypeFormuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypeFormuleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypeFormuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
