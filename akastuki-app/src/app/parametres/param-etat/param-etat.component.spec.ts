import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamEtatComponent } from './param-etat.component';

describe('ParamEtatComponent', () => {
  let component: ParamEtatComponent;
  let fixture: ComponentFixture<ParamEtatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamEtatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamEtatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
