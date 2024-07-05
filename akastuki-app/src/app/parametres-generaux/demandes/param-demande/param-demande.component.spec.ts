import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamDemandeComponent } from './param-demande.component';

describe('ParamDemandeComponent', () => {
  let component: ParamDemandeComponent;
  let fixture: ComponentFixture<ParamDemandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamDemandeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamDemandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
