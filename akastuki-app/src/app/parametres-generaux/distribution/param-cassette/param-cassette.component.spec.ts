import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamCassetteComponent } from './param-cassette.component';

describe('ParamCassetteComponent', () => {
  let component: ParamCassetteComponent;
  let fixture: ComponentFixture<ParamCassetteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamCassetteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamCassetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
