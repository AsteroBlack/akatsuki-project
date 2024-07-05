import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamTypeClientComponent } from './param-type-client.component';

describe('ParamTypeClientComponent', () => {
  let component: ParamTypeClientComponent;
  let fixture: ComponentFixture<ParamTypeClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamTypeClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamTypeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
