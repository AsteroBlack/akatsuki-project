import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalGenerateAdresseLibComponent } from './modal-generate-adresse-lib.component';

describe('ModalGenerateAdresseLibComponent', () => {
  let component: ModalGenerateAdresseLibComponent;
  let fixture: ComponentFixture<ModalGenerateAdresseLibComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalGenerateAdresseLibComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalGenerateAdresseLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
