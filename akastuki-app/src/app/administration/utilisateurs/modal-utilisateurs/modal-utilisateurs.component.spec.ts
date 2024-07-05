import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalRessourcesComponent } from './modal-ressources.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalRessourcesComponent;
  let fixture: ComponentFixture<ModalRessourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalRessourcesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalRessourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
