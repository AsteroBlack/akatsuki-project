import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCommandeDetailsComponent } from './modal-commande-details.component';

describe('ModalCommandeDetailsComponent', () => {
  let component: ModalCommandeDetailsComponent;
  let fixture: ComponentFixture<ModalCommandeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalCommandeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalCommandeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
