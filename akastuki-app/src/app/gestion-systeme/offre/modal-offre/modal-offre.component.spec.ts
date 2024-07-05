import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalOffreComponent } from './modal-offre.component';

describe('ModalOffreComponent', () => {
  let component: ModalOffreComponent;
  let fixture: ComponentFixture<ModalOffreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalOffreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalOffreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
