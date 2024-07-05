import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamArchitectureComponent } from './param-architecture.component';

describe('ParamArchitectureComponent', () => {
  let component: ParamArchitectureComponent;
  let fixture: ComponentFixture<ParamArchitectureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamArchitectureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamArchitectureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
