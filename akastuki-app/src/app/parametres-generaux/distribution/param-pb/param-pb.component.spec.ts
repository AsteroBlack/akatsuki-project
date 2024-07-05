import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamPbComponent } from './param-pb.component';

describe('ParamPbComponent', () => {
  let component: ParamPbComponent;
  let fixture: ComponentFixture<ParamPbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamPbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamPbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
