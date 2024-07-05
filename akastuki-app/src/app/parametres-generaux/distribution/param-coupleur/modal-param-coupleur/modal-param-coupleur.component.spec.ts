import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamCoupleurComponent } from './modal-param-coupleur.component';

describe('ModalParamCoupleurComponent', () => {
  let component: ModalParamCoupleurComponent;
  let fixture: ComponentFixture<ModalParamCoupleurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamCoupleurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamCoupleurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});