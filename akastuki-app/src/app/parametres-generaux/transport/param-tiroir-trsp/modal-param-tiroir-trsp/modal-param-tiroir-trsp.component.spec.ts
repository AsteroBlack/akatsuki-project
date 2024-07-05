import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTiroirDistribComponent } from './modal-tiroir-distrib.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalTiroirDistribComponent;
  let fixture: ComponentFixture<ModalTiroirDistribComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalTiroirDistribComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalTiroirDistribComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});