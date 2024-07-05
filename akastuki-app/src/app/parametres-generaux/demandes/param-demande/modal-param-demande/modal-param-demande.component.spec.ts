import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamDemandeComponent } from './modal-param-demande.component';

describe('ModalParamDemandeComponent', () => {
  let component: ModalParamDemandeComponent;
  let fixture: ComponentFixture<ModalParamDemandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamDemandeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamDemandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
