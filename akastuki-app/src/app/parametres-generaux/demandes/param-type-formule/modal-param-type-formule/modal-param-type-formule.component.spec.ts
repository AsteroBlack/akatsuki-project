import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTypeFormuleComponent } from './modal-type-formule.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalTypeFormuleComponent;
  let fixture: ComponentFixture<ModalTypeFormuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalTypeFormuleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalTypeFormuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});