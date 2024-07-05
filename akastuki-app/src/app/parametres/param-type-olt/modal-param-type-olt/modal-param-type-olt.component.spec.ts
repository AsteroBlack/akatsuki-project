import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTypeOltComponent } from './modal-type-olt.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalTypeOltComponent;
  let fixture: ComponentFixture<ModalTypeOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalTypeOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalTypeOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});