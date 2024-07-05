import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalPdTypeComponent } from './modal-pd-type.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalPdTypeComponent;
  let fixture: ComponentFixture<ModalPdTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalPdTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalPdTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});