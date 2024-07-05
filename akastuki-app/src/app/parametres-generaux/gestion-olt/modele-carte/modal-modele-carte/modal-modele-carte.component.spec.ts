import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalArrondissementComponent } from './modal-arrodissement.component';

describe('ModalArrondissementComponent', () => {
  let component: ModalArrondissementComponent;
  let fixture: ComponentFixture<ModalArrondissementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalArrondissementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalArrondissementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});