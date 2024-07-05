import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamCoupleurComponent } from './param-coupleur.component';

describe('ParamCoupleurComponent', () => {
  let component: ParamCoupleurComponent;
  let fixture: ComponentFixture<ParamCoupleurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamCoupleurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamCoupleurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
