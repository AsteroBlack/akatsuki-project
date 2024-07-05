import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypeCarteComponent } from './param-type-carte.component';

describe('ParamTypeCarteComponent', () => {
  let component: ParamTypeCarteComponent;
  let fixture: ComponentFixture<ParamTypeCarteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypeCarteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypeCarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
