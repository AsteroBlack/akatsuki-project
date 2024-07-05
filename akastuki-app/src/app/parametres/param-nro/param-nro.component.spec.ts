import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamNroComponent } from './param-nro.component';

describe('ParamNroComponent', () => {
  let component: ParamNroComponent;
  let fixture: ComponentFixture<ParamNroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamNroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamNroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
