import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypeOltComponent } from './param-type-olt.component';

describe('ParamTypeOltComponent', () => {
  let component: ParamTypeOltComponent;
  let fixture: ComponentFixture<ParamTypeOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypeOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypeOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
