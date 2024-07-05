import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalComptesComponent } from './modal-comptes.component';

describe('ModalComptesComponent', () => {
  let component: ModalComptesComponent;
  let fixture: ComponentFixture<ModalComptesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalComptesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalComptesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
