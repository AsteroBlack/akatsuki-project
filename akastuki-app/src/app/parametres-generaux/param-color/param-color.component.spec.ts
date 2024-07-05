import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamColorComponent } from './param-color.component';

describe('ParamColorComponent', () => {
  let component: ParamColorComponent;
  let fixture: ComponentFixture<ParamColorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamColorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamColorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
